<script setup lang="ts">
  import {computed} from "vue";

  const props = defineProps({
    piece: {type:String, required:true},
    rank: {type:Number, required:true},
    file: {type:Number, required:true},
    size: {type:Number, required:true}
  })

  const pieceSrc = computed(
      () => {
        switch(props.piece) {
          case "K":  return "src/assets/chess/piece/white-king.png"
          case "Q":  return "src/assets/chess/piece/white-queen.png"
          case "R":  return "src/assets/chess/piece/white-rook.png"
          case "B":  return "src/assets/chess/piece/white-bishop.png"
          case "H":  return "src/assets/chess/piece/white-knight.png"
          case "P":  return "src/assets/chess/piece/white-pawn.png"
          case "k":  return "src/assets/chess/piece/black-king.png"
          case "q":  return "src/assets/chess/piece/black-queen.png"
          case "r":  return "src/assets/chess/piece/black-rook.png"
          case "b":  return "src/assets/chess/piece/black-bishop.png"
          case "h":  return "src/assets/chess/piece/black-knight.png"
          case "p":  return "src/assets/chess/piece/black-pawn.png"
          default: return "";
        }
      }
  )

  const height = computed(() => props.size)
  const width = computed(() => props.size)
  const translateX = computed(() => props.size * props.rank + 'px')
  const translateY = computed(() => props.size * props.file + 'px')
</script>

<template>
  <div class="piece aspect-square flex items-center" :style="{transform: `translate(${translateX}, ${translateY})` }">
    <img :src="pieceSrc" alt="" :width="width" :height="height" />
  </div>
</template>

<style scoped>

  .piece {
    position: absolute;
  }
</style>