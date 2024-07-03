import kotlin.math.abs

data class Pair(val x: Int, val y: Int)

fun main() {
    val (n, m, d) = readln().split(" ").map { it.toInt() }
    val matrix = Array(n) { readln().split(" ").map { it.toInt() }.toIntArray() }

    var maxKilled = 0

    // 궁수 위치 조합
    for (i in 0 ..< m-2) {
        for (j in 0 ..< m-1) {
            for (k in 0 ..< m) {
                val archers = listOf(Pair(n, i), Pair(n, j), Pair(n, k))
                val killed = simulate(matrix, n, m, d, archers)
                maxKilled = maxOf(maxKilled, killed)
            }
        }
    }

    println(maxKilled)
}

fun simulate(originalMatrix: Array<IntArray>, n: Int, m: Int, d: Int, archers: List<Pair>): Int {
    val matrix = originalMatrix.map { it.clone() }.toTypedArray()
    var killed = 0

    while (true) {
        val targets = mutableSetOf<Pair>()
        for (archer in archers) {
            val target = findNearestEnemy(matrix, n, m, d, archer)
            if (target != null) targets.add(target)
        }

        killed += targets.size
        for (target in targets) {
            matrix[target.x][target.y] = 0
        }

        if (!moveEnemies(matrix)) break
    }

    return killed
}

fun findNearestEnemy(matrix: Array<IntArray>, n: Int, m: Int, d: Int, archer: Pair): Pair? {
    var nearestEnemy: Pair? = null
    var minDistance = d + 1

    for (i in 0 ..< n) {
        for (j in 0 ..< m) {
            if (matrix[i][j] == 1) {
                val distance = abs(archer.x - i) + abs(archer.y - j)
                if (distance <= d && (distance < minDistance ||
                    (distance == minDistance && j < (nearestEnemy?.y ?: m)))) {
                    minDistance = distance
                    nearestEnemy = Pair(i, j)
                }
            }
        }
    }
    return nearestEnemy
}

fun moveEnemies(matrix: Array<IntArray>): Boolean {
    var enemyExists = false
//    for (i in matrix.size - 2 downTo 0) {
//        for (j in 0 ..< matrix[0].size) {
//            if (matrix[i][j] == 1) {
//                matrix[i][j] = 0
//                matrix[i+1][j] = 1
//            }
//        }
//    }
    for (i in matrix.size - 1 downTo 1) {
        matrix[i] = matrix[i - 1].clone()
    }
    matrix[0] = IntArray(matrix[0].size)

    for (row in matrix) {
        if (row.any { it == 1 }) {
            enemyExists = true
            break
        }
    }

    return enemyExists
}
