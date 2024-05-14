export class PieceModel {
    type: string = ""
    rank: number
    file: number

    constructor(type: string, rank: number, file: number) {
        this.type = type
        this.rank = rank
        this.file = file
    }

    public getImagePath(): string {
        switch(this.type) {
            case "K":  return "src/chess/assets/piece/white-king.png"
            case "Q":  return "src/chess/assets/piece/white-queen.png"
            case "R":  return "src/chess/assets/piece/white-rook.png"
            case "B":  return "src/chess/assets/piece/white-bishop.png"
            case "H":  return "src/chess/assets/piece/white-knight.png"
            case "P":  return "src/chess/assets/piece/white-pawn.png"
            case "k":  return "src/chess/assets/piece/black   -king.png"
            case "q":  return "src/chess/assets/piece/black-queen.png"
            case "r":  return "src/chess/assets/piece/black-rook.png"
            case "b":  return "src/chess/assets/piece/black-bishop.png"
            case "h":  return "src/chess/assets/piece/black-knight.png"
            case "p":  return "src/chess/assets/piece/black-pawn.png"
            default: return ""
        }
    }

    public getRank(): number {
        return this.rank
    }

    public getFile(): number {
        return this.file
    }
}