package org.example.backend.chess.logic.base

class Square(private var rank: Int, private var file: Int): Comparable<Square> {
    fun move(direction: Square, number: Int = 1): Square {
        val square = Square(this.rank, this.file)
        square.rank += direction.rank * number
        square.file += direction.file * number
        return square
    }

    fun move(direction: Square): Square {
        val square = Square(this.rank, this.file)
        square.rank += direction.rank
        square.file += direction.file
        return square
    }

    fun minus(square: Square): Square {
        val result = Square(this.rank, this.file)
        result.rank -= square.rank
        result.file -= square.file
        return result
    }


    fun getRank() = rank
    fun getFile() = file
    fun getIndex() = 8 * rank + file
    override fun compareTo(other: Square): Int {
        return getIndex().compareTo(other.getIndex())
    }

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is Square -> rank == other.rank && file == other.file
            is Int -> getIndex() == other
            else -> false
        }
    }

    override fun hashCode(): Int {
        return getIndex()
    }

    companion object {

        val diagonalMoves: List<Square> = listOf(
                Square(-1, -1),
                Square(-1, 1),
                Square(1, -1),
                Square(1, 1)
            )


        val axialMoves: List<Square> = listOf(
            Square(-1, 0),
            Square(1, 0),
            Square(0, 1),
            Square(0, -1)
        )

        val completeMoves: List<Square> = axialMoves + diagonalMoves

        val knightMoves: List<Square> = mutableListOf(
            Square(2, 1),
            Square(1, 2),
            Square(-1, 2),
            Square(-2, 1),
            Square(-2, -1),
            Square(-1, -2),
            Square(1, -2),
            Square(2, -1)
        )
    }
}
