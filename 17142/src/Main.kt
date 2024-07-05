import java.util.LinkedList

val dx = arrayOf(0, 0, 1, -1)
val dy = arrayOf(1, -1, 0, 0)

data class PairWithTime(val x: Int, val y: Int, val time: Int)

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val matrix = Array(n) { readln().split(" ").map { it.toInt() }.toIntArray() }
    var minTime = Int.MAX_VALUE

    val viruses = mutableListOf<Pair<Int, Int>>()
    for (i in matrix.indices) {
        for (j in matrix[0].indices) {
            if (matrix[i][j] == 2) {
                viruses.add(Pair(i, j))
            }
        }
    }

    // 홟성 바이러스 조합
    val combinations = generateCombinations(m, viruses)

    for (comb in combinations) {
        val time = simulate(matrix, comb, minTime)
        minTime = minOf(minTime, time)
    }

    if (minTime == Int.MAX_VALUE) println(-1) else println(minTime)
}

fun simulate(originalMatrix: Array<IntArray>, viruses: MutableList<Pair<Int, Int>>, minTime: Int): Int {
    val matrix = originalMatrix.map { it.clone() }.toTypedArray()
    val n = matrix.size
    var emptycount = 0

    for (i in matrix.indices) {
        for (j in matrix[0].indices) {
            when (matrix[i][j]) {
                0 -> {
                    matrix[i][j] = -2
                    emptycount++
                }
                1 -> matrix[i][j] = -1
                2 -> matrix[i][j] = -3
            }
        }
    }

    val queue = LinkedList<PairWithTime>()
    for (virus in viruses) {
        queue.add(PairWithTime(virus.first, virus.second, 0))
        matrix[virus.first][virus.second] = 0 // 활성 바이러스는 0
    }

    var maxTime = 0
    while (queue.isNotEmpty() && emptycount > 0) {
        val (x, y, time) = queue.poll()

        if (minTime <= time) return Int.MAX_VALUE

        for (dir in 0 ..< 4) {
            val nx = x + dx[dir]
            val ny = y + dy[dir]

            if (nx in 0 ..< n && ny in 0 ..< n) {
                when (matrix[nx][ny]) {
                    -2 -> { // 빈칸
                        matrix[nx][ny] = time + 1
                        queue.add(PairWithTime(nx, ny, time + 1))
                        emptycount--
                        maxTime = maxOf(maxTime, time + 1)
                    }
                    -3 -> { // 비활성 바이러스
                        matrix[nx][ny] = time + 1
                        queue.add(PairWithTime(nx, ny, time + 1))
                    }
                }
            }
        }
    }

    return if (emptycount == 0) maxTime else Int.MAX_VALUE
}

fun generateCombinations(m: Int, viruses: MutableList<Pair<Int, Int>>): MutableList<MutableList<Pair<Int, Int>>> {
    val result = mutableListOf<MutableList<Pair<Int, Int>>>()

    fun backtrack(start: Int, list: MutableList<Pair<Int, Int>>) {
        if (list.size == m) {
            result.add(ArrayList(list))
            return
        }

        for (i in start ..< viruses.size) {
            list.add(viruses[i])
            backtrack(i + 1, list)
            list.removeAt(list.lastIndex)
        }
    }

    backtrack(0, mutableListOf())
    return result
}