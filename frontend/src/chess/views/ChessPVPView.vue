<script setup lang="ts">
  import { ref, onMounted } from 'vue';
  import Board from "@/chess/components/Board.vue";
  import { ChessService } from "@/chess/ChessService";
  import { useRoute } from "vue-router";
  import type {SquareModel} from "@/chess/models/SquareModel";

  const route = useRoute();
  const boardModel = ref();
  const gameId = route.params.id.toString();

  onMounted(async () => {
    boardModel.value = await ChessService.getBoard(gameId);
  });

  const onPieceMoved = async (start: SquareModel, end: SquareModel) => {
    console.log(start, end)
    await ChessService.makeMove(gameId, start, end)
    boardModel.value = await ChessService.getBoard(gameId);
  }
</script>

<template>
  <div>Chess PVP</div>
  <Board v-if="boardModel" :model="boardModel" @piece-moved="onPieceMoved" />
</template>

<style scoped>
</style>
