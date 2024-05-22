import type {PieceModel} from "@/chess/models/PieceModel";
import type {SquareModel} from "@/chess/models/SquareModel";

export class GameModel {
    readonly pieces: Array<PieceModel>
    isCheckmate: boolean
    isStalemate: boolean

    constructor(pieces: Array<PieceModel>,
                isCheckmate: boolean,
                isStalemate: boolean
    ) {
        this.pieces = pieces;
        this.isCheckmate = isCheckmate;
        this.isStalemate = isStalemate;
    }
}