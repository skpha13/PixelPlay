<script setup lang="ts">
  import {computed, reactive, type Ref, ref} from "vue";
  import Square from "@/chess/components/Square.vue";
  import {useElementSize} from "@vueuse/core";
  import Piece from "@/chess/components/Piece.vue";
  import {ChessService} from "@/chess/ChessService";
  import {GameModel} from "@/chess/models/GameModel";
  import {SquareModel} from "@/chess/models/SquareModel";
  import {useRoute} from "vue-router";
  import {MoveModel} from "@/chess/models/MoveModel";
  import PromotionTypeSelector from "@/chess/components/PromotionTypeSelector.vue";
  import {Color} from "@/chess/models/Color";
  import type {PromotionType} from "@/chess/models/PromotionType";

  const emit = defineEmits(["pieceMoved"])

  const route = useRoute();
  const gameId = route.params.id.toString();

  const elem = ref(null)
  const squareSize = computed(() => useElementSize(elem).height.value/8)

  const props = defineProps({
    model: {type: GameModel, required:true},
    isPlayingVsEngine :{type:Boolean, default: false},
  })
  const pieces = computed(() => props.model.pieces)

  var lastSquareClicked: SquareModel;
  var possibleSquaresToMove: Ref<SquareModel[]> = ref<SquareModel[]>([])
  const pendingMove = ref<MoveModel>()


  const squareClicked = async (rank: number, file: number) => {
    if(waitingForEngineMove) {
      return
    }
    showPromotionTypeSelector.value = false
    if(isInPossibleSquaresToMove(rank, file)) {
      let currentSquare = new SquareModel(rank, file)
      pendingMove.value = new MoveModel(lastSquareClicked, currentSquare);
      if(isPawnPromotion()) {
        handlePawnPromotion()
      }
      else {
        await makeMove()
      }
    }
    else {
      possibleSquaresToMove.value = await ChessService.getPossibleSquaresToMove(gameId, rank, file)
      lastSquareClicked = new SquareModel(rank, file)
    }
    updateHighlightFlags()
  }

  const isPawnPromotion = () => {
    let isPawn: boolean = false
    for(let piece of pieces.value) {
      if((piece.type == 'p' || piece.type == 'P')
          && piece.file == pendingMove.value!!.startSquare.file
          && piece.rank == pendingMove.value!!.startSquare.rank) {
        isPawn = true
        break
      }
    }

    let isPromotion: boolean = (pendingMove.value!!.endSquare.rank == 0) || (pendingMove.value!!.endSquare.rank == 7)

    return isPawn && isPromotion
  }

  const handlePawnPromotion = () => {
    showPromotionTypeSelector.value = true
    promotionColor.value = Color.WHITE
    for(let piece of pieces.value) {
      if((piece.type == 'p')
          && piece.file == pendingMove.value!!.startSquare.file
          && piece.rank == pendingMove.value!!.startSquare.rank) {
        promotionColor.value = Color.BLACK
        break
      }
    }
  }

  const onPromotionPieceSelected = async (type: PromotionType) => {
    pendingMove.value!!.promotionType = type
    await makeMove()
  }

  var waitingForEngineMove = false;

  const makeMove = async () => {
    await ChessService.makeMove(gameId, pendingMove.value!!)
    emit("pieceMoved")
    possibleSquaresToMove.value = []
    updateHighlightFlags()

    console.log(props.isPlayingVsEngine)

    if(props.isPlayingVsEngine) {
      waitingForEngineMove = true
      await ChessService.makeEngineMove(gameId)
      emit("pieceMoved")
      waitingForEngineMove = false
    }
  }

  let showPromotionTypeSelector = ref(false)
  let promotionColor = ref(Color.WHITE)

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
  <div class="relative m-10 h-[80vh] aspect-square">
    <!-- Chess Board -->
    <div ref="elem" class="board grid grid-cols-8 grid-rows-8 m-10 h-[80vh] aspect-square relative">
      <div v-for="file in 8" :key="'file-' + file">
        <square
            :squareSize="squareSize"
            v-for="rank in 8"
            :key="'rank-' + rank"
            :rank="rank"
            :file="file"
            :highlighted="squaresHighlightFlags[rank-1][file-1]"
            @click="squareClicked(rank-1, file-1)"
        />
      </div>
      <piece
          v-for="piece in pieces"
          :size="squareSize"
          :model="piece"
          style="pointer-events: none"
      />
    </div>
    <!-- Promotion Type Selector Centered -->
    <promotion-type-selector
        v-if="showPromotionTypeSelector"
        :color="promotionColor"
        :size="squareSize"
        @piece-selected="onPromotionPieceSelected"
    />
  </div>
</template>

<style scoped>

</style>