fun main() {
    val (n, m, k) = readln().split(" ").map { it.toInt() }
    val matrix = Array(n) { readln().split(" ").map { it.toInt() }.toIntArray() }
    val calculation = Array(k) { readln().split(" ").map { it.toInt() }.toIntArray() }

    // 연산 permutations 구하기
    val permutations = generatePermutations(k)

    var minScore = Int.MAX_VALUE

    for (perm in permutations) {
        minScore = minOf(minScore, simulate(matrix, calculation, perm))
    }

    println(minScore)
}

fun generatePermutations(k: Int): List<List<Int>> {
    val players = (0..< k).toList()
    val result = mutableListOf<MutableList<Int>>()

    fun backtrack(current: MutableList<Int>) {
        if (current.size == k) {
            val fullOrder = current.toMutableList()
            result.add(fullOrder)
            return
        }

        for (player in players) {
            if (player !in current) {
                current.add(player)
                backtrack(current)
                current.removeAt(current.lastIndex)
            }
        }
    }

    backtrack(mutableListOf())
    return result
}

fun simulate(originalMatrix: Array<IntArray>, calculation: Array<IntArray>, perm: List<Int>): Int {
    val matrix = Array(originalMatrix.size) { originalMatrix[it].clone() }
    var minScore = Int.MAX_VALUE

    for (index in perm) {
        val (r, c, s) = calculation[index]

        var firstX = r-s-1
        var firstY = c-s-1
        var secondX = r+s-1
        var secondY = c+s-1

        while (firstX < secondX && firstY < secondY) {
            val temp = matrix[firstX][firstY]

            // 왼쪽 변 이동
            for (i in firstX ..< secondX) {
                matrix[i][firstY] = matrix[i+1][firstY]
            }

            // 아래 변 이동
            for (i in firstY ..< secondY) {
                matrix[secondX][i] = matrix[secondX][i+1]
            }

            // 오른쪽 변 이동
            for (i in secondX downTo firstX + 1) {
                matrix[i][secondY] = matrix[i-1][secondY]
            }

            // 위쪽 변 이동
            for (i in secondY downTo firstY + 1) {
                matrix[firstX][i] = matrix[firstX][i-1]
            }

            matrix[firstX][firstY+1] = temp

            firstX++
            firstY++
            secondX--
            secondY--
        }
    }

    for (row in matrix) {
        minScore = minOf(row.sum(), minScore)
    }

    return minScore
}