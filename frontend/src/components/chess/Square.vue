<script setup lang="ts">
  import {computed} from "vue";
  import colors from "tailwindcss/colors";

  const props = defineProps({
    rank: {type:Number, required:true},
    file: {type:Number, required:true},
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


</script>

<template>
  <div class="square aspect-square" :style="{backgroundColor}">

  </div>
</template>

<style scoped>

</style>