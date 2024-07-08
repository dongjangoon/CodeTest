val dx = intArrayOf(0, 1, 0, -1) // 왼, 아래, 오, 위
val dy = intArrayOf(-1, 0, 1, 0)

// 모래 분산 비율 (아래, 왼(5), 위, 오(x))
val spreadX = intArrayOf(-2, -1, -1, -1, 0, 1, 1, 1, 2, 0)
val spreadY = intArrayOf(0, -1, 0, 1, -2, -1, 0, 1, 0, -1)
val spreadPercent = intArrayOf(2, 10, 7, 1, 5, 10, 7, 1, 2, 0)

fun main() {
    val n = readln().toInt()
    val grid = Array(n) { readln().split(" ").map { it.toInt() }.toIntArray() }

    var answer = 0
    var x = n / 2
    var y = n / 2
    var dir = 0
    var moveCount = 0
    var stepSize = 1

    while (x > 0 || y > 0) {
        for (i in 0 until stepSize) {
            x += dx[dir]
            y += dy[dir]
            if (x < 0 || y < 0) break

            val sand = grid[x][y]
            grid[x][y] = 0
            var spreadSand = 0

            for (j in 0 until 9) {
                val nx = x + rotateX(spreadX[j], spreadY[j], dir)
                val ny = y + rotateY(spreadX[j], spreadY[j], dir)
                val amount = (sand * spreadPercent[j] / 100)

                if (nx !in 0 until n || ny !in 0 until n) {
                    answer += amount
                } else {
                    grid[nx][ny] += amount
                }
                spreadSand += amount
            }

            // alpha 위치 처리
            val alphaX = x + dx[dir]
            val alphaY = y + dy[dir]
            val alphaAmount = sand - spreadSand
            if (alphaX !in 0 until n || alphaY !in 0 until n) {
                answer += alphaAmount
            } else {
                grid[alphaX][alphaY] += alphaAmount
            }
        }

        dir = (dir + 1) % 4
        moveCount++
        if (moveCount == 2) {
            stepSize++
            moveCount = 0
        }
    }

    println(answer)
}

fun rotateX(x: Int, y: Int, dir: Int): Int = when (dir) {
    0 -> x
    1 -> -y
    2 -> -x
    3 -> y
    else -> x
}

fun rotateY(x: Int, y: Int, dir: Int): Int = when (dir) {
    0 -> y
    1 -> x
    2 -> -y
    3 -> -x
    else -> y
}