<script setup lang="ts">
  import {computed} from "vue";
  import colors from "tailwindcss/colors";
  import { useParentElement } from '@vueuse/core'

  const props = defineProps({
    squareSize: {type:Number, required:true},
    rank: {type:Number, required:true},
    file: {type:Number, required:true}
  })

  console.log(props.squareSize)

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

  const height = computed(() => props.squareSize + 'px')
</script>

<template>
  <div class="square aspect-square" :style="{backgroundColor, height}">
  </div>
</template>

<style scoped>
</style>