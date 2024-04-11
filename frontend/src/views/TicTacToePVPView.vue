<script setup lang="ts">
import {reactive, ref} from "vue";
import Logo from "@/components/Logo.vue";
import GlassButon from "@/components/GlassButon.vue";

const winner = ref<string | null>(null);
const isTie = ref(false);
const gameover = ref(false);
const currentPlayer = ref('X');

var board = reactive([
  ['', '', ''],
  ['', '', ''],
  ['', '', '']
]);

const checkTie = () => {
  for (let i = 0; i < 3; i++) {
    for (let j = 0; j < 3; j++) {
      if (!board[i][j]) {
        return false;
      }
    }
  }
  return true;
}

const checkWin = () => {
  const a = currentPlayer.value;

  for (let i = 0; i < 3; i++) {
    if (board[i].every(cell => cell === a)) return true;
    if (board.every(row => row[i] === a)) return true;
  }

  // Check diagonals
  if (board[0][0] === a && board[1][1] === a && board[2][2] === a) return true;
  if (board[0][2] === a && board[1][1] === a && board[2][0] === a) return true;

  return false;
};

const reset = () => {
  board = [
    ['', '', ''],
    ['', '', ''],
    ['', '', '']
  ];
  currentPlayer.value = 'X';
  gameover.value = false;
  winner.value = null;
}

const playMove = (row: number, col: number) => {
  if (!board[row][col] && !winner.value) {
    board[row][col] = currentPlayer.value;
    if (checkWin()) {
      winner.value = currentPlayer.value;
    } else if (checkTie()) {
      isTie.value = true;
    } else {
      currentPlayer.value = currentPlayer.value === 'X' ? 'O' : 'X';
    }
  }
}

</script>

<template>
  <Logo title="Tic Tac Toe" class="mb-10" />

  <div class="flex flex-col items-center justify-center">
    <p class=""> Current Player: <span class=""> {{ currentPlayer }} </span> </p>
    <div class="clear-both" v-for="(row, rowIndex) of board" :key="rowIndex">
      <div class="w-40 h-40 float-left mr-[-1px] mb-[-1px] leading-[10rem]
                  text-center border border-black hover:cursor-pointer text-8xl"
           v-for="(cell, cellIndex) of row" :key="cellIndex"
           :class="{ 'cell-x': cell === 'X', 'cell-o': cell === 'O' }" :disabled="cell !== null"
           @click="playMove(rowIndex, cellIndex)">
        {{ cell }}
      </div>
    </div>

    <div class="">
      <p v-if="winner">{{ winner }} wins!</p>
      <p v-else-if="isTie">It's a tie!</p>
      <GlassButon @clicked="reset"
                  title="Reset Game"
                  link=""
                  class="mt-6" />
    </div>
  </div>
</template>

<style scoped>
.cell-x {
  color: #ff004d;
}

.cell-o {
  color: #6e6cff;
}

button {
  margin-top: 20px;
  font-size: 16px;
  padding: 10px;
  border-radius: 5px;
  background-color: #ccc;
  border: none;
  cursor: pointer;
}

</style>