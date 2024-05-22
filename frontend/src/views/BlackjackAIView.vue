<template>
  <Logo title="Blackjack AI" class="mb-10" />
  <div class="blackjack-ai">
    <div v-if="!gameState" class="betting">
    <label for="betAmount" class="bet-label">Enter your bet: </label>
    <input v-model="betAmount" id="betAmount" type="number" :disabled="gameStarted" />
    <button @click="startGame" :disabled="gameStarted" class="start-button">Start Game</button>
  </div>
    <CasinoDivider />
    <div class="turn-indicator" v-if="gameState">
      <p v-if="turn === 'player'" class="highlight">Player's Turn</p>
      <p v-if="turn === 'dealer'" class="highlight">Dealer's Turn</p>
    </div>
    <CasinoDivider />
    <div class="players">
      <div class="player" v-if="gameState">
        <Logo title="Player" class="player-title" />
        <CasinoDivider />
        <p class="score">Bet: {{ betAmount }}€ </p>
        <div class="cards">
          <img v-for="card in gameState.playerHand.hand" :key="card.suit + card.value" :src="getCardImage(card)" alt="Card image" class="card"/>
        </div>
        <p class="score">Score: {{ gameState.playerScore }}</p>
        <p v-if="gameState.playerScore === 21" class="blackjack21">Blackjack!</p>
        <p v-if="gameState.playerBust" class="bust">Busted!</p>
        <div class="buttons">
          <button @click="playerHit" :disabled="turn !== 'player' || gameState.playerBust">Hit</button>
          <button @click="playerStand" :disabled="turn !== 'player' || gameState.playerBust">Stand</button>
          <button @click="playerDoubleDown" :disabled="turn !== 'player' || gameState.playerBust || gameState.playerHand.hand.length !== 2">Double Down</button>
          <!-- <button @click="playerSplit" :disabled="turn !== 'player' || gameState.playerBust || gameState.playerHand.hand.length !== 2 || !canSplit">Split</button> -->
        </div>
      </div>
      <CasinoDivider />
      <div class="player" v-if="gameState">
        <Logo title="Dealer" class="player-title" />
        <CasinoDivider />
        <p class="score">&nbsp;</p>
        <div class="cards">
          <img v-for="(card, index) in gameState.dealerHand.hand" :key="card.suit + card.value" :src="getCardImage(index === 0 && turn === 'player' ? 'back' : card)" alt="Card image" class="card"/>
        </div>
        <p class="score">Score: {{ dealerScore }}</p>
        <p v-if="dealerScore === 21" class="blackjack21">Blackjack!</p>
        <p v-if="gameState.dealerBust" class="bust">Busted!</p>
      </div>
    </div>
    <CasinoDivider />
    <div v-if="winnerMessage" class="winner-message">
      <h2>{{ winnerMessage }}</h2>
      <CasinoDivider />
      <GlassButon @clicked="resetGame" title="Reset Game" link="" class="mt-6" />
      <CasinoDivider />
    </div>
    <button v-if="gameState && !winnerMessage" @click="toggleTutorial" class="tutorial-button">Tutorial</button>
    <div v-if="showTutorial" class="tutorial" @mouseleave="hideTutorial" @click="hideTutorial">
      <p>Welcome to Blackjack AI!</p>
      <p>Rules:</p>
      <ul>
        <li>The goal is to have a hand value closest to 21 without exceeding it.</li>
        <li>You are playing against the dealer.</li>
        <li>You can "Hit" (draw a card), "Stand" (keep your current hand) or "Double Down".</li>
        <!--<li>You can "Hit" (draw a card), "Stand" (keep your current hand), "Double Down" or "Split".</li> -->
        <li>"Double Down": Double your bet and draw only one more card.</li>
        <!-- <li>"Split": If you have two cards of the same value, split them into two hands.</li> -->
        <li>If you exceed 21, you "Bust" and lose.</li>
        <li>The highest hand value that is 21 or less wins.</li>
        <li>The dealer's first card is face down until you stand.</li>
        <li>The dealer's turn begins when you stand.</li>
        <li>The dealer must continue hitting until reaching at least 17.</li>
        <li>Enter your bet before starting the game.</li>
      </ul>
    </div>
    <CasinoDivider v-if="!winnerMessage" />
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
    const betAmount = ref<number>(0);
    const gameStarted = ref(false);
    const turn = ref<string>('player');
    const playerScore = ref<number>(0);
    const dealerScore = ref<number>(0);
    const canSplit = ref<boolean>(false);

    const startGame = async () => {
      if (betAmount.value <= 0) {
        alert('Please enter a valid bet amount.');
        return;
      }
      const response = await axios.post('http://localhost:8080/api/blackjackAI/start', { bet: betAmount.value });
      gameState.value = response.data;
      gameStarted.value = true;
      winnerMessage.value = null;
      playerScore.value = gameState.value.playerScore;
      dealerScore.value = calculateScore(gameState.value.dealerHand.hand);
      canSplit.value = gameState.value.playerHand.hand.length === 2 && gameState.value.playerHand.hand[0].value === gameState.value.playerHand.hand[1].value;
    };

    const resetGame = () => {
      gameState.value = null;
      winnerMessage.value = null;
      showTutorial.value = false;
      betAmount.value = 0;
      gameStarted.value = false;
      turn.value = 'player';
      playerScore.value = 0;
      dealerScore.value = 0;
      canSplit.value = false;
    };

    const switchTurn = () => {
      turn.value = turn.value === 'player' ? 'dealer' : 'player';
      dealerScore.value = gameState.value.dealerScore;
    };

    const playerHit = async () => {
      const response = await axios.post('http://localhost:8080/api/blackjackAI/hit');
      gameState.value = response.data;
      playerScore.value = gameState.value.playerScore;
      dealerScore.value = calculateScore(gameState.value.dealerHand.hand);
      if (playerScore.value > 21) {
        gameState.value.playerBust = true;
        switchTurn();
        dealerPlay();
      }
    };

    const playerStand = async () => {
      const response = await axios.post('http://localhost:8080/api/blackjackAI/stand');
      gameState.value = response.data;
      dealerScore.value = gameState.value.dealerScore;
      gameState.value.playerStand = true;
      switchTurn();
      dealerPlay();
    };

    const playerDoubleDown = async () => {
      betAmount.value *= 2;
      const response = await axios.post('http://localhost:8080/api/blackjackAI/doubleDown');
      gameState.value = response.data;
      playerScore.value = gameState.value.playerScore;
      dealerScore.value = gameState.value.dealerScore;
      gameState.value.playerStand = true;
      switchTurn();
      dealerPlay();
    };

    const playerSplit = async () => {
      const response = await axios.post('http://localhost:8080/api/blackjackAI/split');
      gameState.value = response.data;
      dealerScore.value = calculateScore(gameState.value.dealerHand.hand);
      canSplit.value = false;
    };

    const dealerPlay = async () => {
      
        const response = await axios.post('http://localhost:8080/api/blackjackAI/stand');
        gameState.value = response.data;
        dealerScore.value = gameState.value.dealerScore;
      
      checkWinner();
    };

    const checkWinner = () => {
      const playerFinalScore = playerScore.value;
      const dealerFinalScore = dealerScore.value;

      if (playerFinalScore > 21) {
        winnerMessage.value = "Player busts! Dealer wins!";
      } else if (dealerFinalScore > 21) {
        winnerMessage.value = "Dealer busts! Player wins!";
      } else if (playerFinalScore > dealerFinalScore) {
        winnerMessage.value = "Player wins!";
      } else if (dealerFinalScore > playerFinalScore) {
        winnerMessage.value = "Dealer wins!";
      } else {
        winnerMessage.value = "It's a tie!";
      }
    };

    const calculateScore = (hand: any[]) => {
      let score = 0;
      let aces = 0;
      const start = turn.value === 'player' ? 1 : 0;
      for (let i = start; i < hand.length; i++) {
    const card = hand[i];
    if (['Jack', 'Queen', 'King'].includes(card.value)) {
      score += 10;
    } else if (card.value === 'Ace') {
      aces += 1;
    } else {
      score += parseInt(card.value, 10);
    }
  }

  for (let i = 0; i < aces; i++) {
    if (score + 11 <= 21) {
      score += 11;
    } else {
      score += 1;
    }
  }

  return score;
    };

    const getCardImage = (card: any) => {
      if (card === 'back') {
        return '/src/assets/cardGames/cardDeck/cards/back.png';
      }
      return `/src/assets/cardGames/cardDeck/cards/${card.suit}_${card.value}.png`;
    };

    const toggleTutorial = () => {
      showTutorial.value = !showTutorial.value;
    };

    const hideTutorial = () => {
      showTutorial.value = false;
    };

    onMounted(() => {
      resetGame();
    });

    return {
      gameState,
      winnerMessage,
      showTutorial,
      betAmount,
      gameStarted,
      turn,
      playerScore,
      dealerScore,
      canSplit,
      startGame,
      resetGame,
      playerHit,
      playerStand,
      playerDoubleDown,
      playerSplit,
      dealerPlay,
      checkWinner,
      calculateScore,
      getCardImage,
      toggleTutorial,
      hideTutorial,
    };
  },
});
</script>

<style scoped>

.bet-label {
  font-size: 18px;
  color: yellow;
  padding-left: 20px; 
  padding-right: 20px;
  font-weight: bold;
  font-size: 22px;
  text-shadow: 1px 1px 2px black;
}

.start-button {
  margin-left: 22px;
}

.blackjack-ai {
  text-align: center;
  background-color: #0b3d91; 
  padding: 20px;
  border-radius: 10px;
}

.betting {
  margin-bottom: 20px;
  font-weight: bold;
  font-size: 22px;
  text-shadow: 1px 1px 2px black;
  
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
  margin-bottom: 1rem;
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
  font-size: 24px;
  color: red;
  font-weight: bold;
  margin-bottom: 1rem;
}

.blackjack21 {
  font-size: 24px;
  color: rgb(0, 0, 0);
  font-weight: bold;
  margin-bottom: 1rem;
}

</style>
