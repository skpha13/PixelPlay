import type {SquareModel} from "@/chess/models/SquareModel";
import {PromotionType} from "@/chess/models/PromotionType";

export class MoveModel {
    startSquare: SquareModel
    endSquare: SquareModel
    promotionType: PromotionType

    constructor(startSquare: SquareModel, endSquare: SquareModel) {
        this.startSquare = startSquare
        this.endSquare = endSquare
        this.promotionType = PromotionType.NONE
    }
}