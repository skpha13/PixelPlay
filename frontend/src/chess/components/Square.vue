<script setup lang="ts">
import {computed, ref, watch} from "vue";
  import colors from "tailwindcss/colors";
  import { useParentElement } from '@vueuse/core'

  const props = defineProps({
    squareSize: {type:Number, required:true},
    rank: {type:Number, required:true},
    file: {type:Number, required:true},
    highlighted: {type:Boolean, required: true, default:false},
  })

  var isHighlighted = ref(false)

  watch(() => props.highlighted, () => {
    isHighlighted.value = props.highlighted
    console.log(isHighlighted.value)
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

  const height = computed(() => props.squareSize + 'px')
</script>

<template>
  <div class="square aspect-square" :style="{backgroundColor, height}">
    <div v-if="isHighlighted" class="highlight bg-gray-500 opacity-50" :style="{height}"/>
  </div>
</template>

<style scoped>
</style>