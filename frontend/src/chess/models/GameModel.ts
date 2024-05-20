import type {PieceModel} from "@/chess/models/PieceModel";

export class GameModel {
    readonly pieces: Array<PieceModel>

    constructor(pieces: Array<PieceModel>) {
        this.pieces = pieces;
    }
}