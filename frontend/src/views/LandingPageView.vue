<script setup lang="ts">
import Logo from "@/components/Logo.vue";
import GamePreview from "@/components/GamePreview.vue";
import ChoiceMenu from "@/components/ChoiceMenu.vue";
import {reactive, ref} from "vue";

// ========== ARGUMENT PASSING FOR CHOICE MENU ==========
const choiceParameters = reactive<{
  gameTitle: string,
  linkToAI: string,
  linkToPVP: string
}>({
  gameTitle: '',
  linkToAI: '',
  linkToPVP: ''
});
// ======================================================

// ========== CHOICE MENU ==========
const showChoice = ref(false);
const hideChoiceForGame = () => {
  showChoice.value = false;
}
const showChoiceForGame = (payload: any) => {
  showChoice.value = !showChoice.value;
  choiceParameters.gameTitle = payload.gameTitle;
  choiceParameters.linkToAI = payload.linkToAI;
  choiceParameters.linkToPVP = payload.linkToPVP;
}
// =================================
</script>

<template>
  <Logo title="PixelPlay" class="mb-10"/>
  <div class="flex flex-col lg:flex-row items-center gap-12">


  <GamePreview @showChoice="payload => showChoiceForGame(payload)"
               title="Tic Tac Toe"
               img-src="src/assets/tic-tac-toe.jpeg"
               link-ai="/tic-tac-toe-ai"
               link-pvp="/tic-tac-toe-pvp"/>

  <GamePreview @showChoice="payload => showChoiceForGame(payload)"
               title="Connect Four"
               img-src="src/assets/connect-four.jpg"
               link-ai="/connect-four-ai"
               link-pvp="/connect-four-pvp"/>

  <GamePreview @showChoice="payload => showChoiceForGame(payload)"
               title="Chess"
               img-src="src/chess/assets/logo.png"
               link-ai="/chess/ai"
               link-pvp="/chess/pvp"/>

  <GamePreview @showChoice="payload => showChoiceForGame(payload)"
                 title="Blackjack"
                 img-src="src/assets/cardGames/blackjack/blackjack.png"
                 link-ai="/blackjack-ai"
                 link-pvp="/blackjack-pvp"/>

  </div>
  <ChoiceMenu v-if="showChoice"
              @clicked-close-button="hideChoiceForGame"
              :link-to-ai="choiceParameters.linkToAI"
              :link-to-pvp="choiceParameters.linkToPVP"
              :game-title="choiceParameters.gameTitle" />
</template>