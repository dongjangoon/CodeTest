data class Point(val x: Int, val y: Int)
data class Turn(val time: Int, val direction: Char)

fun main() {
    val n = readln().toInt()
    val k = readln().toInt()
    val apples = mutableSetOf<Point>()
    repeat(k) {
        val (x, y) = readln().split(" ").map { it.toInt() }
        apples.add(Point(x, y))
    }

    val l = readln().toInt()
    val turns = mutableListOf<Turn>()
    repeat(l) {
        val (time, direction) = readln().split(" ")
        turns.add(Turn(time.toInt(), direction[0]))
    }

    println(simulate(n, apples, turns))
}

fun simulate(n: Int, apples: MutableSet<Point>, turns: List<Turn>): Int {
    val snake = ArrayDeque<Point>()
    snake.addFirst(Point(1, 1))

    var direction = 0 // 0: 오른쪽, 1: 아래쪽, 2: 왼쪽, 3: 위쪽
    val dx = arrayOf(0, 1, 0, -1)
    val dy = arrayOf(1, 0, -1, 0)

    var time = 0
    var turnIndex = 0

    while (true) {
        time++

        val head = snake.first()
        val newHead = Point(head.x + dx[direction], head.y + dy[direction])

        // 벽과 충돌 체크
        if (newHead.x < 1 || newHead.y < 1 || newHead.x > n || newHead.y > n) {
            return time
        }

        // 자기 자신과 충돌 체크
        if (newHead in snake) {
            return time
        }

        snake.addFirst(newHead)

        // 사과 체크
        if (newHead in apples) {
            apples.remove(newHead)
        } else {
            snake.removeLast()
        }

        // 방향 전환 체크
        if (turnIndex < turns.size && time == turns[turnIndex].time) {
            direction = when (turns[turnIndex].direction) {
                'L' -> (direction - 1 + 4) % 4
                'D' -> (direction + 1) % 4
                else -> direction
            }
            turnIndex++
        }
    }
}