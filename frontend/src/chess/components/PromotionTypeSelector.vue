<script setup lang="ts">
import {Color} from "@/chess/models/Color";
import {computed, type PropType} from "vue";
import Piece from "@/chess/components/Piece.vue";
import {PieceModel} from "@/chess/models/PieceModel";
import {PromotionType} from "@/chess/models/PromotionType";

  const props = defineProps({
    size: {type: Number, required: true},
    color: {type: Number as PropType<Color>, required: true}
  })

  const emit = defineEmits(["pieceSelected"])

  const queenType = computed(() => {
    if (props.color == Color.WHITE) {
      return 'Q'
    }
    else {
      return 'q'
    }
  })

  const rookType = computed(() => {
    if (props.color == Color.WHITE) {
      return 'R'
    }
    else {
      return 'r'
    }
  })

  const bishopType = computed(() => {
    if (props.color == Color.WHITE) {
      return 'B'
    }
    else {
      return 'b'
    }
  })

  const knightType = computed(() => {
    if (props.color == Color.WHITE) {
      return 'H'
    }
    else {
      return 'h'
    }
  })

  const queenModel: PieceModel = new PieceModel(queenType.value, 0, 0);
  const rookModel: PieceModel = new PieceModel(rookType.value, 0, 1);
  const bishopModel: PieceModel = new PieceModel(bishopType.value, 0, 2);
  const knightModel: PieceModel = new PieceModel(knightType.value, 0, 3);

  const pieceClicked = (type: PromotionType) => {
    emit("pieceSelected", type)
  }

  const width = computed(() => 4 * props.size + "px")
  const height = computed(() => props.size + "px")


</script>

<template>
    <div class="">
      <piece :size="props.size" :model="queenModel" @click="pieceClicked(PromotionType.QUEEN)" class="bg-blue-800"/>
      <piece :size="props.size" :model="rookModel" @click="pieceClicked(PromotionType.ROOK)" class="bg-blue-800"/>
      <piece :size="props.size" :model="bishopModel" @click="pieceClicked(PromotionType.BISHOP)" class="bg-blue-800"/>
      <piece :size="props.size" :model="knightModel" @click="pieceClicked(PromotionType.KNIGHT)" class="bg-blue-800"/>
    </div>

</template>

<style scoped>
</style>