//This code uses backtracking to solve the n queens problem

const val N = 8

fun solveNQueens(n: Int): List<List<String>> {
    val solutions = mutableListOf<List<String>>()
    val chessBoard = Array(n) { CharArray(n) { '.' } }
    backtrack(chessBoard, 0, solutions)
    return solutions
}

private fun backtrack(chessBoard: Array<CharArray>, row: Int, solutions: MutableList<List<String>>) {
    if (row == chessBoard.size) {
        solutions.add(chessBoard.map { String(it) })
        return
    }
    for (col in chessBoard.indices) {
        if (isValid(chessBoard, row, col)) {
            chessBoard[row][col] = 'Q'
            backtrack(chessBoard, row + 1, solutions)
            chessBoard[row][col] = '.'
        }
    }
}

private fun isValid(chessBoard: Array<CharArray>, row: Int, col: Int): Boolean {
    //check if there is a queen in the same column
    for (i in 0 until row) {
        if (chessBoard[i][col] == 'Q') return false
    }
    //check for queens in the upper left diagonal
    for (i in row - 1 downTo 0) {
        val j = row - i + col
        if (j >= 0 && j < chessBoard.size && chessBoard[i][j] == 'Q') return false
    }
    //check for queens in the upper right diagonal
    for (i in row - 1 downTo 0) {
        val j = col - (row - i)
        if (j >= 0 && j < chessBoard.size && chessBoard[i][j] == 'Q') return false
    }
    return true
}

fun main() {
    val solutions = solveNQueens(N)
    for (solution in solutions) {
        for (row in solution) {
            println(row)
        }
        println()
    }
    println("Total Solutions: ${solutions.size}")
}
