<script setup lang="ts">
import axios from 'axios'
import {ref} from "vue";
import Logo from "@/components/Logo.vue";

const board = ref<string[][]>()
const isTie = ref<boolean>(false)
const winner = ref<string>("")

const testController = async () => {
  try {
    const response = await axios.post('http://localhost:8080/tic-tac-toe/play-move', {
      "board": [
        "xox",
        "oxo",
        "---"
      ],
      "winner": null,
      "isTie": false
    });

    board.value = stringArrayToMatrix(response.data.board)
    isTie.value = response.data.isTie
    winner.value = response.data.winner

    console.log(board.value)
  } catch (e: any) {
    console.log(e)
  }
}

const stringArrayToMatrix = (array: string[]): string[][] => {
  return array.map(str => str.split(''))
}

testController()
</script>

<template>
  <h1>Tic Tac Toe AI</h1>
</template>