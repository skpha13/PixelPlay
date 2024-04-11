<script setup lang="ts">

import ConnectFourGameBoard from "@/views/ConnectFourGameBoardView.vue";

import {ref} from "vue";

const table = ref([ [0,0,0,0,0,0,0],
  [0,0,0,0,0,0,0],
  [0,0,0,0,0,0,0],
  [0,0,0,0,0,0,0],
  [0,0,0,0,0,0,0],
  [0,0,0,0,0,0,0]])

const playerTurn = ref(1);

const switchPlayerTurn = () => {
  if (playerTurn.value == 1) {
    playerTurn.value = 2;
    return;
  }
  playerTurn.value = 1;
  return;
}

const putPieceInColumn = (j : number) => {
  if (j < 0 || j >= table.value[0].length)
    return;
  for (let i = 0; i < table.value.length - 1; i++) {
    if (table.value[i][j] != 0)
      return;
    if (table.value[i+1][j] != 0) {
      table.value[i][j] = playerTurn.value;
      switchPlayerTurn();
      return;
    }
  }
  table.value[5][j] = playerTurn.value;
  switchPlayerTurn();
}

const checkWin = (column : number) => {
  let line : number = -1;
  for (let i = 0; i < table.value.length; i++) {
    if (table.value[i][column] != 0) {
      line = i;
      break;
    }
  }

  if (line == -1)
    return false;

  return checkWinLine(line, column, 1, 0) || checkWinLine(line, column, 0, 1)
      || checkWinLine(line, column, 1, 1) || checkWinLine(line, column, -1, 1);
}

const validatePosition = (line: number, column: number) => {
  return line >= 0 && line < table.value.length && column >= 0 && column < table.value[0].length;
};

function checkWinLine(line:number, column:number, x:number, y:number) {
  // i -> how many left positions should we look for
  // j -> how many right positions should we look for
  let leftPositions = 0, rightPositions = 3;
  let isWin;
  while (leftPositions < 4) {
    isWin = true;
    for (let i = 1; i < leftPositions + 1; i++) {
      if (validatePosition(line - x * i, column - y * i) && table.value[line][column] != table.value[line - x * i][column - y * i]) {
        isWin = false;
      }
    }
    for (let j = 1; j < rightPositions + 1; j++) {
      if (validatePosition(line + x * j, column + y * j) && table.value[line][column] != table.value[line + x * j][column + y * j]) {
        isWin = false;
      }
    }
    leftPositions += 1;
    rightPositions -= 1;
    if (isWin)
      return true;
  }
  return false;
}

//  ----------- Diagonally up
// putPieceInColumn(0);
// putPieceInColumn(1);
// putPieceInColumn(1);
// putPieceInColumn(2);
// putPieceInColumn(3);
// putPieceInColumn(2);
// putPieceInColumn(2);
// putPieceInColumn(3);
// putPieceInColumn(3);
// putPieceInColumn(4);
// putPieceInColumn(4);
// putPieceInColumn(4);
// putPieceInColumn(3);
//
// console.log(table.value);
// console.log(checkWin(3));

// ------------- Vertically
// putPieceInColumn(1);
// putPieceInColumn(2);
// putPieceInColumn(1);
// putPieceInColumn(2);
// putPieceInColumn(1);
// putPieceInColumn(2);
// putPieceInColumn(1);
//
// console.log(table.value);
// console.log(checkWin(1));

// ------------ Horizontally
// putPieceInColumn(1);
// putPieceInColumn(1);
// putPieceInColumn(2);
// putPieceInColumn(2);
// putPieceInColumn(3);
// putPieceInColumn(3);
// putPieceInColumn(4);

// console.log(table.value);
// console.log(checkWin(4));

// -------------- Diagonally down
// putPieceInColumn(1);
// putPieceInColumn(1);
// putPieceInColumn(1);
// putPieceInColumn(1);
// putPieceInColumn(2);
// putPieceInColumn(2);
// putPieceInColumn(3);
// putPieceInColumn(2);
// putPieceInColumn(5);
// putPieceInColumn(3);
// putPieceInColumn(6);
// putPieceInColumn(4);
//
// console.log(table.value);
// console.log(checkWin(4));

</script>

<template>
  <h1>Connect Four PVP</h1>
  <ConnectFourGameBoard />
</template>

<style scoped>

</style>