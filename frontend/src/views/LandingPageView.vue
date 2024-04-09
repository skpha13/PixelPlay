<script setup lang="ts">
import Logo from "@/components/Logo.vue";
import GamePreview from "@/components/GamePreview.vue";
import GlassButon from "@/components/GlassButon.vue";

</script>

<template>
  <Logo class="mb-10"/>
  <GamePreview title="Tic Tac Toe"
               img-src="src/assets/tic-tac-toe.jpeg"
               route-name="ticTacToe"/>
</template>