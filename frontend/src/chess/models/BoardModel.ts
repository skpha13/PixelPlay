import type {PieceModel} from "@/chess/models/PieceModel";

export class BoardModel {
    readonly pieces: Array<PieceModel>

    constructor(pieces: Array<PieceModel>) {
        this.pieces = pieces;
    }
}