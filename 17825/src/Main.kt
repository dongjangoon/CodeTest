class Yut {
    private val board = intArrayOf(
        0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 0,
        10, 13, 16, 19, 25, 30, 35, 40, 0,
        20, 22, 24, 25, 30, 35, 40, 0,
        30, 28, 27, 26, 25, 30, 35, 40, 0
    )
    private val nextIndex = intArrayOf(
        1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 21,
        23, 24, 25, 26, 27, 28, 29, 21, 21,
        31, 32, 33, 26, 27, 28, 29, 21,
        39, 40, 41, 42, 26, 27, 28, 29, 21
    )
    private val blueStart = intArrayOf(5, 10, 15)
    private val blueNext = intArrayOf(22, 30, 38)
    private var maxScore = 0

    fun solution(dice: IntArray) {
        dfs(dice, IntArray(4), 0, 0)
        println(maxScore)
    }

    private fun dfs(dice: IntArray, horses: IntArray, turn: Int, score: Int) {
        if (turn == 10) {
            maxScore = maxOf(maxScore, score)
            return
        }

        for (i in horses.indices) {
            var cur = horses[i]
            if (cur == 21) continue // 이미 도착한 말은 움직이지 않음

            var move = dice[turn]
            if (cur in blueStart) {
                cur = blueNext[blueStart.indexOf(cur)]
                move--
            }

            var next = cur
            for (j in 0 until move) {
                if (next == 21) break // 도착점이면 멈춤
                next = nextIndex[next]
            }

            if (next != 21 && horses.contains(next)) continue

            val temp = horses[i]
            horses[i] = next
            dfs(dice, horses, turn + 1, score + board[next])
            horses[i] = temp
        }
    }
}

fun main() {
    val dice = readln().split(" ").map { it.toInt() }.toIntArray()
    Yut().solution(dice)
}