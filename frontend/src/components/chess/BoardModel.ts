import type {PieceModel} from "@/components/chess/PieceModel";

export class BoardModel {
    readonly pieces: Array<PieceModel>

    constructor(pieces: Array<PieceModel>) {
        this.pieces = pieces;
    }
}