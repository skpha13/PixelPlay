<script setup lang="ts">
  import { ref, onMounted } from 'vue';
  import Board from "@/chess/components/Board.vue";
  import { ChessService } from "@/chess/ChessService";
  import { useRoute } from "vue-router";

  const route = useRoute();
  const boardModel = ref();

  onMounted(async () => {
    const gameId = route.params.id.toString();
    boardModel.value = await ChessService.getBoard(gameId);
  });
</script>

<template>
  <div>Chess PVP</div>
  <Board v-if="boardModel" :model="boardModel" />
</template>

<style scoped>
</style>
