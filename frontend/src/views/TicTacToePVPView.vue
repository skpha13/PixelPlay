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
  isTie.value = false;
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
      <div class="w-28 h-28 leading-[7rem] sm:w-40 sm:h-40 sm:leading-[10rem]
                  float-left mr-[-1px] mb-[-1px]
                  text-center border border-black hover:cursor-pointer text-8xl"
           v-for="(cell, cellIndex) of row" :key="cellIndex"
           :class="{ 'text-pink-500': cell === 'X', 'text-indigo-500': cell === 'O' }" :disabled="cell !== null"
           @click="playMove(rowIndex, cellIndex)">
        {{ cell }}
      </div>
    </div>

    <div>
      <p v-if="winner" class="text-center">{{ winner }} wins!</p>
      <p v-else-if="isTie" class="text-center">It's a tie!</p>
      <GlassButon @clicked="reset"
                  title="Reset Game"
                  link=""
                  class="mt-6" />
    </div>
  </div>
</template>