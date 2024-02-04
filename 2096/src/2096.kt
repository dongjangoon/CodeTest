import java.lang.Integer.max
import java.lang.Math.min
import java.util.*

fun main(args: Array<String>) {
    val sc = Scanner(System.`in`)

    val n = sc.nextInt()
    val board = Array(n) { IntArray(3) }
    val d = Array(n) { IntArray(3) }
    val e = Array(n) { IntArray(3) }

    for (i in 0 until n) {
        for (j in 0 until 3) {
            board[i][j] = sc.nextInt()
            e[i][j] = 10000000
        }
    }

    for (i in 0 until 3) {
        d[0][i] = board[0][i]
        e[0][i] = board[0][i]
    }

    for (i in 0 until n-1) {
        for (j in 0 until 3) {
            when (j) {
                0 -> {
                    d[i + 1][j] = max(board[i+1][j] + d[i][j], d[i+1][j])
                    e[i + 1][j] = min(board[i+1][j] + e[i][j], e[i+1][j])
                    d[i + 1][j+1] = max(board[i+1][j+1] + d[i][j], d[i+1][j+1])
                    e[i + 1][j+1] = min(board[i+1][j+1] + e[i][j], e[i+1][j+1])
                }
                2 -> {
                    d[i + 1][j-1] = max(board[i+1][j-1] + d[i][j], d[i+1][j-1])
                    e[i + 1][j-1] = min(board[i+1][j-1] + e[i][j], e[i+1][j-1])
                    d[i + 1][j] = max(board[i+1][j] + d[i][j], d[i+1][j])
                    e[i + 1][j] = min(board[i+1][j] + e[i][j], e[i+1][j])
                }
                else -> {
                    d[i + 1][j-1] = max(board[i+1][j-1] + d[i][j], d[i+1][j-1])
                    e[i + 1][j-1] = min(board[i+1][j-1] + e[i][j], e[i+1][j-1])
                    d[i + 1][j] = max(board[i+1][j] + d[i][j], d[i+1][j])
                    e[i + 1][j] = min(board[i+1][j] + e[i][j], e[i+1][j])
                    d[i + 1][j+1] = max(board[i+1][j+1] + d[i][j], d[i+1][j+1])
                    e[i + 1][j+1] = min(board[i+1][j+1] + e[i][j], e[i+1][j+1])
                }
            }
        }
    }

    var max = -1
    var min = 10000000

    for (i in 0 until 3) {
        if (d[n-1][i] > max) max = d[n-1][i]
        if (e[n-1][i] < min) min = e[n-1][i]
    }

    print("$max $min")

}