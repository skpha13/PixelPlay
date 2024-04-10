<script setup lang="ts">
import {FontAwesomeIcon} from "@fortawesome/vue-fontawesome";

const props = defineProps<{
  gameTitle: string,
  linkToPvp: string,
  linkToAi: string
}>();

const emits = defineEmits<{
  (e: 'clickedCloseButton') : void
}>()
</script>

<template>
  <div class="z-50 fixed top-0 left-0 w-full h-full flex items-center justify-center">
    <div class="bg-white/10 backdrop-blur rounded-lg text-center
                border border-neutral-100 drop-shadow-sm
                px-4 pt-8 w-96 h-1/2 md:w-[500px]
                flex flex-col items-center justify-end">

      <font-awesome-icon @click="emits('clickedCloseButton')"
                         icon="fa-solid fa-xmark"
                         class="fixed z-50 top-0 right-0 m-2
                                font-extrabold text-4xl text-white drop-shadow-lg
                                hover:cursor-pointer"/>
      <h2 class="flex-1 font-extrabold text-5xl text-white drop-shadow-lg">{{ props.gameTitle }}</h2>

      <div class="w-full h-1/2 flex flex-col items-center justify-center mb-2">
        <div class="mb-6 bg-white/20 hover:bg-white/30 border rounded-lg p-4 px-6 w-full">
          <RouterLink :to="props.linkToPvp"
                      class="font-bold text-4xl"> Player vs Player </RouterLink>
        </div>

        <div class="bg-white/20 hover:bg-white/30 border rounded-lg p-4 px-6 w-full">
          <RouterLink :to="props.linkToAi"
                      class="font-bold text-4xl"> Player vs AI </RouterLink>
        </div>
      </div>
    </div>
  </div>
</template>
