<script setup lang="ts">
  import {ref} from "vue";
  import {ChessService} from "@/chess/ChessService";
  import router from "@/router";

  const createGame = async () => {
    let gameId = await ChessService.createGame()
    console.log(gameId)
    if(gameId)
      await router.push(`/chess/pvp/${gameId}`)
  }
</script>

<template>
  <div class="flex flex-col items-center justify-center">
    <button
        @click = "createGame"
        class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-full">
      Start New Game
    </button>
  </div>
</template>

<style scoped>

</style>