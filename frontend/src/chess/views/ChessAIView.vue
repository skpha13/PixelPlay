<script setup lang="ts">
import { ref, onMounted } from 'vue';
import Board from "@/chess/components/Board.vue";
import { ChessService } from "@/chess/ChessService";
import { useRoute } from "vue-router";
import type {SquareModel} from "@/chess/models/SquareModel";
import Dialog from "@/chess/components/Dialog.vue";

const route = useRoute();
const boardModel = ref();
const gameId = route.params.id.toString();

onMounted(async () => {
  boardModel.value = await ChessService.getBoard(gameId);
});

const showDialog = ref<boolean>(false)

const dialogText = ref("")
const onPieceMoved = async () => {
  boardModel.value = await ChessService.getBoard(gameId);
  console.log(boardModel.value)
  if(boardModel.value.isCheckmate) {
    showDialog.value = true
    dialogText.value = "Checkmate"
  }
  if(boardModel.value.isStalemate) {
    showDialog.value = true
    dialogText.value = "Stalemate"
  }
}


</script>

<template>
  <div>Chess PVP</div>
  <Board v-if="boardModel" :model="boardModel" @piece-moved="onPieceMoved" :is-playing-vs-engine="true" />
  <Dialog v-if="showDialog" :text="dialogText"/>
</template>

<style scoped>
</style>
