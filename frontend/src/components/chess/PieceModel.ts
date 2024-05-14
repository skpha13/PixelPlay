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
            case "K":  return "src/assets/chess/piece/white-king.png"
            case "Q":  return "src/assets/chess/piece/white-queen.png"
            case "R":  return "src/assets/chess/piece/white-rook.png"
            case "B":  return "src/assets/chess/piece/white-bishop.png"
            case "H":  return "src/assets/chess/piece/white-knight.png"
            case "P":  return "src/assets/chess/piece/white-pawn.png"
            case "k":  return "src/assets/chess/piece/black-king.png"
            case "q":  return "src/assets/chess/piece/black-queen.png"
            case "r":  return "src/assets/chess/piece/black-rook.png"
            case "b":  return "src/assets/chess/piece/black-bishop.png"
            case "h":  return "src/assets/chess/piece/black-knight.png"
            case "p":  return "src/assets/chess/piece/black-pawn.png"
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