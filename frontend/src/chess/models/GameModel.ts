import type {PieceModel} from "@/chess/models/PieceModel";
import type {SquareModel} from "@/chess/models/SquareModel";

export class GameModel {
    readonly pieces: Array<PieceModel>

    constructor(pieces: Array<PieceModel>) {
        this.pieces = pieces;
    }
}