<script setup lang="ts">
  import {computed, reactive, type Ref, ref} from "vue";
  import Square from "@/chess/components/Square.vue";
  import {useElementSize} from "@vueuse/core";
  import Piece from "@/chess/components/Piece.vue";
  import {ChessService} from "@/chess/ChessService";
  import {GameModel} from "@/chess/models/GameModel";
  import {SquareModel} from "@/chess/models/SquareModel";
  import {useRoute} from "vue-router";

  const emit = defineEmits(["pieceMoved"])

  const route = useRoute();
  const gameId = route.params.id.toString();

  const elem = ref(null)
  const squareSize = computed(() => useElementSize(elem).height.value/8)

  const props = defineProps({
    model: {type: GameModel, required:true},
  })
  const pieces = computed(() => props.model.pieces)

  var lastSquareClicked: SquareModel;
  var possibleSquaresToMove: Ref<SquareModel[]> = ref<SquareModel[]>([])
  const squareClicked = async (rank: number, file: number) => {
    if(isInPossibleSquaresToMove(rank, file)) {
      let currentSquare = new SquareModel(rank, file)
      await ChessService.makeMove(gameId, lastSquareClicked, currentSquare)
      emit("pieceMoved", lastSquareClicked, currentSquare)
      possibleSquaresToMove.value = []
    }
    else {
      possibleSquaresToMove.value = await ChessService.getPossibleSquaresToMove(gameId, rank, file)
      lastSquareClicked = new SquareModel(rank, file)
    }
    updateHighlightFlags()
  }

  const isInPossibleSquaresToMove = (rank: number, file:number) => {
    for(let square of possibleSquaresToMove.value) {
      if(square.rank === rank && square.file === file) {
        return true
      }
    }
    return false
  }

  const updateHighlightFlags = () => {
    for(let rank=0; rank < 8; rank ++) {
      for(let file = 0; file < 8; file ++) {
        squaresHighlightFlags[rank][file] = false
      }
    }
    for(let square of possibleSquaresToMove.value) {
      squaresHighlightFlags[square.rank][square.file] = true
    }
  }

  const squaresHighlightFlags = reactive(
    Array.from(
    { length: 8 },
    () => Array(8).fill(false)));

</script>

<template>
  <div ref="elem" class="board grid grid-cols-8 grid-rows-8 m-10 h-[80vh] aspect-square">
    <div v-for="file in 8">
      <square :squareSize="squareSize" v-for="rank in 8"
              :rank="rank"
              :file="file"
              :highlighted="squaresHighlightFlags[rank-1][file-1]"
              @click="squareClicked(rank-1, file-1)"/>
    </div>

    <piece v-for="piece in pieces" :size="squareSize" :model="piece" style="pointer-events: none"/>
  </div>
</template>

<style scoped>

</style>