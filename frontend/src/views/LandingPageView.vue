<script setup lang="ts">
import Logo from "@/components/Logo.vue";
import GamePreview from "@/components/GamePreview.vue";
import ChoiceMenu from "@/components/ChoiceMenu.vue";

const showChoiceForGame = (payload: any) => {
  console.log(payload);
}
</script>

<template>
  <Logo class="mb-10"/>
  <GamePreview @showChoice="payload => showChoiceForGame(payload)"
               title="Tic Tac Toe"
               img-src="src/assets/tic-tac-toe.jpeg"
               link-ai="test"
               link-pvp="/tic-tac-toe-pvp"/>

  <ChoiceMenu link-to-ai="/tic-tac-toe-ai" link-to-pvp="/tic-tac-toe-pvp" game-title="Tic Tac Toe" />
</template>