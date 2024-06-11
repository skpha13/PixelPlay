<script setup lang="ts">

import {ref} from "vue";
import GlassButon from "@/components/GlassButon.vue";
import Logo from "@/components/Logo.vue";

const board = ref([ [0,0,0,0,0,0,0],
                          [0,0,0,0,0,0,0],
                          [0,0,0,0,0,0,0],
                          [0,0,0,0,0,0,0],
                          [0,0,0,0,0,0,0],
                          [0,0,0,0,0,0,0]]);

const playerTurn = ref(1);

const winner = ref(0);

const switchPlayerTurn = () => {
  playerTurn.value = playerTurn.value === 1 ? 2 : 1;
};

const dropPiece = (colIndex : number) => {
  if (colIndex < 0 || colIndex >= board.value[0].length) return;
  if (winner.value !== 0) return;
  for (let i = board.value.length - 1; i >= 0 ; i--) {
    if (board.value[i][colIndex] === 0) {
      board.value[i][colIndex] = playerTurn.value;
      if (checkWin(i, colIndex)) {
        winner.value = playerTurn.value;
      }
      else if (checkTie()) {
        winner.value = 3;
      }
      else {
        switchPlayerTurn();
      }
      return;
    }
  }
}

const checkWin = (line : number, column : number) => {
  const dir = [
      [1, 0], // vertical
      [0, 1], // horizontal
      [1, 1], // diagonal \
      [-1, 1] // diagonal /
  ]

  for (const [dx, dy] of dir) {
    let count = 1;
    count += countDirection(line, column, dx, dy);
    count += countDirection(line, column, -dx, -dy);
    if (count >= 4) return true;
  }
  return false;
}

function countDirection(line: number, column: number, dx: number, dy: number) {
  const player = board.value[line][column];
  let count = 0;
  let x = line + dx;
  let y = column + dy;
  while (x >= 0 && x < 6 && y >= 0 && y < 7 && board.value[x][y] === player) {
    count += 1;
    x += dx;
    y += dy;
  }
  return count;
}

const checkTie = () => {
  for (let i = 0; i < 7; ++i) {
    if (board.value[0][i] == 0)
      return false;
  }
  return true;
}

const reset = () => {
  board.value = [
      [0,0,0,0,0,0,0],
      [0,0,0,0,0,0,0],
      [0,0,0,0,0,0,0],
      [0,0,0,0,0,0,0],
      [0,0,0,0,0,0,0],
      [0,0,0,0,0,0,0]
  ];

  playerTurn.value = 1;
  winner.value = 0;
}

</script>

<template>
  <div class="connect-four flex flex-col items-center justify-center">
    <Logo title="Connect Four PVP" class="mb-10"/>
    <div class="game-board bg-blue-800 border-blue-800 border-8 inline-block">
      <div
          v-for="(row, rowIndex) in board"
          :key="rowIndex"
          class="flex border-blue-800 border-4"
      >
        <div
            v-for="(cell, colIndex) in row"
            :key="colIndex"
            class="cell border-4 border-blue-800 w-12 h-12 flex justify-center items-center cursor-pointer"
            @click="dropPiece(colIndex)"
            :class="{
            'bg-gray-200' : cell === 0,
            'bg-pink-500': cell === 1,
            'bg-yellow-500': cell === 2
          }"
        ></div>
      </div>
    </div>
    <div v-if="winner !== 0" class="winner mt-4">
      <p v-if="winner === 1 || winner === 2" class="text-lg font-bold">Congratulations Player {{ winner }}! You win!</p>
      <p v-if="winner === 3" class="text-lg font-bold">It is a TIE!</p>

    </div>

    <div class="flex flex-row items-center justify-center" >
      <GlassButon @clicked="reset"
                  title="Reset Game"
                  link=""
                  class="mt-6" />
    </div>
  </div>
</template>

<style scoped>
.connect-four {
  text-align: center;
}

.game-board {
  display: inline-block;
}

.cell {
  width: 50px;
  height: 50px;
  border-radius: 50%;
}

.winner {
  margin-top: 20px;
  font-size: 24px;
}
</style>