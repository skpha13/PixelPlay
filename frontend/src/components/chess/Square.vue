<script setup lang="ts">
  import {computed} from "vue";
  import colors from "tailwindcss/colors";

  const props = defineProps({
    rank: {type:Number, required:true},
    file: {type:Number, required:true},
    piece: {type:String}
  })

  const backgroundColor = computed(
      () => {
        if((props.file + props.rank) % 2 == 0) {
          return colors.green["300"]
        }
        else {
          return colors.sky["300"]
        }
      }
  )

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


</script>

<template>
  <div class="square aspect-square flex items-stretch" :style="{backgroundColor}">
    <img v-if="pieceSrc" :src="pieceSrc"/>
  </div>
</template>

<style scoped>

</style>