package com.example.pixelplay.chess.mechanics

class Cell(private var rank: Int, private var file: Int) {
    fun move(direction: Cell): Cell {
        val cell = Cell(this.rank, this.file)
        cell.rank += direction.rank
        cell.file += direction.file
        return cell
    }

    fun getRank() = rank
    fun getFile() = file


    companion object {

        val diagonalMoves: List<Cell> = listOf(
                Cell(-1, -1),
                Cell(-1, 1),
                Cell(1, -1),
                Cell(1, 1)
            )


        val axialMoves: List<Cell> = listOf(
            Cell(-1, 0),
            Cell(1, 0),
            Cell(0, 1),
            Cell(0, -1)
        )

        val completeMoves: List<Cell> = axialMoves + diagonalMoves

        val knightMoves: List<Cell> = mutableListOf(
            Cell(2, 1),
            Cell(1, 2),
            Cell(-1, 2),
            Cell(-2, 1),
            Cell(-2, -1),
            Cell(-1, -2),
            Cell(1, -2),
            Cell(2, -1)
        )
    }
}
