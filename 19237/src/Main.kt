data class Shark(var x: Int, var y: Int, var direction: Int)
data class Smell(var sharkId: Int, var time: Int)

val dx = intArrayOf(0, -1, 1, 0, 0) // 위, 아래, 왼, 오
val dy = intArrayOf(0, 0, 0, -1, 1)

fun main() {
    val (n, m, k) = readln().split(" ").map { it.toInt() }
    val grid = Array(n) { IntArray(n) }
    val smells = Array(n) { Array(n) { Smell(0, 0) } }
    val sharks = Array(m + 1) { Shark(0, 0, 0) }
    val priorities = Array(m + 1) { Array(5) { IntArray(4) } }

    // 격자 정보 입력
    for (i in 0 until n) {
        val row = readln().split(" ").map { it.toInt() }
        for (j in 0 until n) {
            grid[i][j] = row[j]
            if (grid[i][j] != 0) {
                sharks[grid[i][j]] = Shark(i, j, 0)
                smells[i][j] = Smell(grid[i][j], k)
            }
        }
    }

    // 상어 초기 방향 입력
    readln().split(" ").map { it.toInt() }.forEachIndexed { index, dir ->
        sharks[index + 1].direction = dir
    }

    // 우선순위 입력
    for (i in 1..m) {
        for (j in 1..4) {
            priorities[i][j] = readln().split(" ").map { it.toInt() }.toIntArray()
        }
    }

    var time = 0
    while (time < 1000) {
        // 상어 이동
        val newPositions = mutableMapOf<Pair<Int, Int>, Int>()
        for (id in 1..m) {
            val shark = sharks[id]
            if (shark.x == -1) continue // 제거된 상어

            var nextX = -1
            var nextY = -1
            var nextDir = -1

            // 빈칸 찾기
            for (dir in priorities[id][shark.direction]) {
                val nx = shark.x + dx[dir]
                val ny = shark.y + dy[dir]
                if (nx in 0 until n && ny in 0 until n && smells[nx][ny].time == 0) {
                    nextX = nx
                    nextY = ny
                    nextDir = dir
                    break
                }
            }

            // 자신의 냄새가 있는 칸 찾기
            if (nextX == -1) {
                for (dir in priorities[id][shark.direction]) {
                    val nx = shark.x + dx[dir]
                    val ny = shark.y + dy[dir]
                    if (nx in 0 until n && ny in 0 until n && smells[nx][ny].sharkId == id) {
                        nextX = nx
                        nextY = ny
                        nextDir = dir
                        break
                    }
                }
            }

            // 상어 이동
            shark.x = nextX
            shark.y = nextY
            shark.direction = nextDir

            // 충돌 처리
            val key = Pair(nextX, nextY)
            if (key in newPositions) {
                if (newPositions[key]!! > id) {
                    sharks[newPositions[key]!!].x = -1 // 제거
                    newPositions[key] = id
                } else {
                    sharks[id].x = -1 // 제거
                }
            } else {
                newPositions[key] = id
            }
        }

        // 냄새 감소 및 새로운 냄새 추가
        for (i in 0 until n) {
            for (j in 0 until n) {
                if (smells[i][j].time > 0) {
                    smells[i][j].time--
                }
                if (smells[i][j].time == 0) {
                    smells[i][j].sharkId = 0
                }
            }
        }
        for ((pos, id) in newPositions) {
            smells[pos.first][pos.second] = Smell(id, k)
        }

        time++

        // 1번 상어만 남았는지 확인
        if (sharks.count { it.x != -1 } == 2) { // 0번 인덱스 포함
            println(time)
            return
        }
    }

    println(-1)
}