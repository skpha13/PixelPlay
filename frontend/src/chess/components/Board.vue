<script setup lang="ts">
  import {computed, ref} from "vue";
  import Square from "@/chess/components/Square.vue";
  import {useElementSize} from "@vueuse/core";
  import Piece from "@/chess/components/Piece.vue";
  import {ChessService} from "@/chess/ChessService";
  import {GameModel} from "@/chess/models/GameModel";

  const elem = ref(null)
  const squareSize = computed(() => useElementSize(elem).height.value/8)

  const props = defineProps({
    model: {type: GameModel, required:true},
  })
  const pieces = computed(() => props.model.pieces)

</script>

<template>
  <div ref="elem" class="board grid grid-cols-8 grid-rows-8 m-10 h-[80vh] aspect-square">
    <div v-for="i in 8">
      <square :squareSize="squareSize" v-for="j in 8" :rank="i" :file="j"/>
    </div>

    <piece v-for="piece in pieces" :size="squareSize" :model="piece"/>
  </div>
</template>

<style scoped>

</style>