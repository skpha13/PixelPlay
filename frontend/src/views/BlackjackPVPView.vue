<template>
  <Logo title="Blackjack PVP" class="mb-10" />
  <div class="blackjack-pvp">
    <CasinoDivider />
    <div class="turn-indicator" v-if="gameState">
      <p :class="{ highlight: turn === 1 }">Player 1's Turn</p>
      <p :class="{ highlight: turn === 2 }">Player 2's Turn</p>
    </div>
    <CasinoDivider />
    <div class="players">
      <div class="player" v-if="gameState">
        <Logo title="Player 1" class="player-title" />
        <div class="cards">
          <img v-for="card in gameState.player1Hand.hand" :key="card.suit + card.value" :src="getCardImage(card)" alt="Card image" class="card"/>
        </div>
        <p class="score">Score: {{ gameState.player1Score === 21 ? 'Blackjack (21)!' : gameState.player1Score }}</p>
        <p v-if="gameState.player1Bust" class="bust">Busted!</p>
        <div class="buttons">
          <button @click="player1Hit" :disabled="gameState.player1Stand || gameState.player1Bust || turn !== 1">Hit</button>
          <button @click="player1Stand" :disabled="gameState.player1Stand || gameState.player1Bust || turn !== 1">Stand</button>
        </div>
      </div>
      <CasinoDivider />
      <div class="player" v-if="gameState">
        <Logo title="Player 2" class="player-title" />
        <div class="cards">
          <img v-for="card in gameState.player2Hand.hand" :key="card.suit + card.value" :src="getCardImage(card)" alt="Card image" class="card"/>
        </div>
        <p class="score">Score: {{ gameState.player2Score === 21 ? 'Blackjack (21)!' : gameState.player2Score }}</p>
        <p v-if="gameState.player2Bust" class="bust">Busted!</p>
        <div class="buttons">
          <button @click="player2Hit" :disabled="gameState.player2Stand || gameState.player2Bust || turn !== 2">Hit</button>
          <button @click="player2Stand" :disabled="gameState.player2Stand || gameState.player2Bust || turn !== 2">Stand</button>
        </div>
      </div>
    </div>
    <CasinoDivider />
    <div v-if="winnerMessage" class="winner-message">
      <h2>{{ winnerMessage }}</h2>
      <CasinoDivider />
      <GlassButon @clicked="startGame" title="Reset Game" link="" class="mt-6" />
      <CasinoDivider />
    </div>
    
    <button @click="toggleTutorial" class="tutorial-button">Tutorial</button>
    <div v-if="showTutorial" class="tutorial" @mouseleave="hideTutorial" @click="hideTutorial">
      <p>Welcome to Blackjack PVP!</p>
      <p>Rules:</p>
      <ul>
        <li>The goal is to have a hand value closest to 21 without exceeding it.</li>
        <li>Each player takes turns to either "Hit" (draw a card) or "Stand" (keep their current hand).</li>
        <li>If you exceed 21, you "Bust" and lose.</li>
        <li>The player with the highest hand value that is 21 or less wins.</li>
        <li>The game alternates turns between Player 1 and Player 2.</li>
        <li>If a player stands or busts, their turn is skipped.</li>
        <li>The current player's turn is highlighted.</li>
      </ul>
    </div>
    <CasinoDivider />
  </div>
</template>

<script lang="ts">
import { onMounted } from 'vue';
import { defineComponent, ref } from 'vue';
import Logo from "@/components/Logo.vue";
import GlassButon from "@/components/GlassButon.vue";
import CasinoDivider from "@/components/cardGames/blackjack/CasinoDivider.vue";
import axios from 'axios';

export default defineComponent({
  components: {
    Logo,
    GlassButon,
    CasinoDivider
  },
  
  setup() {
    const gameState = ref<any>(null);
    const winnerMessage = ref<string | null>(null);
    const showTutorial = ref(false);
    const turn = ref<number>(1);

    const startGame = async () => {
      const response = await axios.post('http://localhost:8080/api/blackjackPVP/start');
      gameState.value = response.data;
      winnerMessage.value = null;
      turn.value = Math.random() < 0.5 ? 1 : 2; // Randomly select who starts
    };

    const switchTurn = () => {
      if (turn.value === 1) {
        if (!gameState.value.player2Stand && !gameState.value.player2Bust) {
          turn.value = 2;
        }
      } else {
        if (!gameState.value.player1Stand && !gameState.value.player1Bust) {
          turn.value = 1;
        }
      }
    };

    const player1Hit = async () => {
      const response = await axios.post('http://localhost:8080/api/blackjackPVP/player1/hit');
      gameState.value = response.data;
      checkWinner();
      switchTurn();
    };

    const player1Stand = async () => {
      const response = await axios.post('http://localhost:8080/api/blackjackPVP/player1/stand');
      gameState.value = response.data;
      checkWinner();
      switchTurn();
    };

    const player2Hit = async () => {
      const response = await axios.post('http://localhost:8080/api/blackjackPVP/player2/hit');
      gameState.value = response.data;
      checkWinner();
      switchTurn();
    };

    const player2Stand = async () => {
      const response = await axios.post('http://localhost:8080/api/blackjackPVP/player2/stand');
      gameState.value = response.data;
      checkWinner();
      switchTurn();
    };

    const checkWinner = () => {
      if (gameState.value.player1Stand && gameState.value.player2Stand) {
        const player1Score = gameState.value.player1Bust ? 0 : gameState.value.player1Score;
        const player2Score = gameState.value.player2Bust ? 0 : gameState.value.player2Score;

        if (gameState.value.player1Bust && gameState.value.player2Bust) {
          winnerMessage.value = "It's a tie!";
        } else if (gameState.value.player1Bust) {
          winnerMessage.value = "Player 2 wins!";
        } else if (gameState.value.player2Bust) {
          winnerMessage.value = "Player 1 wins!";
        } else if (player1Score > player2Score) {
          winnerMessage.value = "Player 1 wins!";
        } else if (player2Score > player1Score) {
          winnerMessage.value = "Player 2 wins!";
        } else {
          winnerMessage.value = "It's a tie!";
        }
      }

      if (gameState.value.player1Bust && gameState.value.player2Bust) {
        winnerMessage.value = "It's a tie!";
      }

      if (gameState.value.player1Bust && gameState.value.player2Stand) {
        winnerMessage.value = "Player 2 wins!";
      }

      if (gameState.value.player2Bust && gameState.value.player1Stand) {
        winnerMessage.value = "Player 1 wins!";
      }
    };

    const getCardImage = (card: any) => {
      return `/src/assets/cardGames/cardDeck/cards/${card.suit}_${card.value}.png`;
    };

    const toggleTutorial = () => {
      showTutorial.value = !showTutorial.value;
    };

    const hideTutorial = () => {
      showTutorial.value = false;
    };

    onMounted(() => {
      startGame();
    });

    return {
      gameState,
      winnerMessage,
      player1Hit,
      player1Stand,
      player2Hit,
      player2Stand,
      getCardImage,
      showTutorial,
      toggleTutorial,
      hideTutorial,
      startGame,
      turn,
    };
  },
});
</script>

<style scoped>
.blackjack-pvp {
  text-align: center;
  background-color: #0b3d91;
  padding: 20px;
  border-radius: 10px;
}

.players {
  display: flex;
  justify-content: space-around;
}

.player {
  flex: 1;
  margin: 0 20px;
  padding: 20px;
  background-color: #27ae60;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
}

.cards {
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
  margin-bottom: 20px;
}

.card {
  width: 100px;
  margin: 5px;
  border: 1px solid #fff;
  border-radius: 5px;
}

.buttons {
  display: flex;
  justify-content: center;
  gap: 10px;
}

button {
  padding: 10px 20px;
  font-size: 16px;
  background-color: #2980b9;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s, transform 0.3s;
}

button:disabled {
  background-color: #95a5a6;
  cursor: not-allowed;
}

button:hover:not(:disabled) {
  background-color: #3498db;
  transform: scale(1.05);
}

.score {
  font-size: 24px;
  font-weight: bold;
  color: #fff;
}

.winner-message h2 {
  color: #ffcc00;
  margin-top: 1.5rem;
  font-weight: bold;
  font-size: 30px;
}

.tutorial-button {
  margin-top: 20px;
  padding: 10px 20px;
  font-size: 20px;
  background-color: #f39c12;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.tutorial-button:hover {
  background-color: #e67e22;
}

.tutorial {
  background-color: white;
  color: black;
  border: 1px solid #ccc;
  padding: 20px;
  border-radius: 5px;
  position: absolute;
  top: 750px;
  left: 50%;
  transform: translateX(-50%);
  width: 300px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
  z-index: 1000;
}

.turn-indicator p {
  font-size: 20px;
  color: yellow;
}

.turn-indicator .highlight {
  font-weight: bold;
  font-size: 22px;
  text-shadow: 1px 1px 2px black;
}

.bust {
  color: red;
  font-weight: bold;
}
</style>
