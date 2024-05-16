<script setup lang="ts">
  import {computed, ref} from "vue";
  import Square from "@/chess/board/Square.vue";
  import {useElementSize} from "@vueuse/core";
  import {PieceModel} from "@/chess/models/PieceModel";
  import Piece from "@/chess/piece/Piece.vue";
  import {BoardModel} from "@/chess/models/BoardModel";

  const elem = ref(null)
  const squareSize = computed(() => useElementSize(elem).height.value/8)

  const boardModel = ref(new BoardModel([
      new PieceModel('b', 1, 1)
  ]))

</script>

<template>
  <div ref="elem" class="board grid grid-cols-8 grid-rows-8 m-10 h-[80vh] aspect-square">
    <div v-for="i in 8">
      <square :squareSize="squareSize" v-for="j in 8" :rank="i" :file="j"/>
    </div>

    <piece v-for="piece in boardModel.pieces" :size="squareSize" :model="piece"/>
  </div>
</template>

<style scoped>

</style>