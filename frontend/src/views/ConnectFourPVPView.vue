<script setup lang="ts">

import {ref} from "vue";
import GlassButon from "@/components/GlassButon.vue";

const table = ref([ [0,0,0,0,0,0,0],
  [0,0,0,0,0,0,0],
  [0,0,0,0,0,0,0],
  [0,0,0,0,0,0,0],
  [0,0,0,0,0,0,0],
  [0,0,0,0,0,0,0]])

const playerTurn = ref(1);

const winner = ref(0);

const switchPlayerTurn = () => {
  playerTurn.value = playerTurn.value === 1 ? 2 : 1;
};

const dropPiece = (colIndex : number) => {
  if (colIndex < 0 || colIndex >= table.value[0].length) return;
  if (winner.value !== 0) return;
  for (let i = table.value.length - 1; i >= 0 ; i--) {
    if (table.value[i][colIndex] === 0) {
      table.value[i][colIndex] = playerTurn.value;
      if (checkWin(i, colIndex)) {
        winner.value = playerTurn.value;
      } else {
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
  const player = table.value[line][column];
  let count = 0;
  let x = line + dx;
  let y = column + dy;
  while (x >= 0 && x < 6 && y >= 0 && y < 7 && table.value[x][y] === player) {
    count += 1;
    x += dx;
    y += dy;
  }
  return count;
}

const reset = () => {
  table.value = [
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
    <h1 class="text-3xl font-bold mb-4">Connect Four</h1>
    <div class="game-board border border-black inline-block">
      <div
          v-for="(row, rowIndex) in table"
          :key="rowIndex"
          class="flex"
      >
        <div
            v-for="(cell, colIndex) in row"
            :key="colIndex"
            class="cell border border-black w-12 h-12 flex justify-center items-center cursor-pointer"
            @click="dropPiece(colIndex)"
            :class="{
            'bg-pink-500': cell === 1,
            'bg-indigo-500': cell === 2
          }"
        ></div>
      </div>
    </div>
    <div v-if="winner !== 0" class="winner mt-4">
      <p class="text-lg font-bold">Congratulations Player {{ winner }}! You win!</p>

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
}

.winner {
  margin-top: 20px;
  font-size: 24px;
}
</style>