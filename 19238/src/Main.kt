import java.util.LinkedList

data class Point(val x: Int, val y: Int)
data class Passenger(val start: Point, val end: Point)

val dx = intArrayOf(-1, 1, 0, 0)
val dy = intArrayOf(0, 0, -1, 1)

fun main() {
    val (n, m, gas) = readln().split(" ").map { it.toInt() }
    val matrix = Array(n) { readln().split(" ").map { it.toInt() }.toIntArray() }
    val (taxiX, taxiY) = readln().split(" ").map { it.toInt() - 1 }
    var taxi = Point(taxiX, taxiY)
    var remainingGas = gas

    val passengers = List(m) {
        val (startX, startY, endX, endY) = readln().split(" ").map { it.toInt() - 1 }
        Passenger(Point(startX, startY), Point(endX, endY))
    }.toMutableList()

    for (i in 0 until m) {
        val (nearestPassenger, distance) = findNearestPassenger(matrix, taxi, passengers)
        if (nearestPassenger == null || distance == -1 || remainingGas < distance) {
            println(-1)
            return
        }

        remainingGas -= distance
        taxi = nearestPassenger.start

        val distanceToDest = bfs(matrix, taxi, nearestPassenger.end)
        if (distanceToDest == -1 || remainingGas < distanceToDest) {
            println(-1)
            return
        }

        remainingGas += distanceToDest
        taxi = nearestPassenger.end
        passengers.remove(nearestPassenger)
    }

    println(remainingGas)
}

fun findNearestPassenger(matrix: Array<IntArray>, taxi: Point, passengers: List<Passenger>): Pair<Passenger?, Int> {
    val n = matrix.size
    val queue = LinkedList<Point>()
    val visited = Array(n) { BooleanArray(n) { false } }
    val distances = Array(n) { IntArray(n) { -1 } }

    queue.add(taxi)
    visited[taxi.x][taxi.y] = true
    distances[taxi.x][taxi.y] = 0

    var nearestPassengers = mutableListOf<Passenger>()
    var minDistance = Int.MAX_VALUE

    while (queue.isNotEmpty()) {
        val current = queue.poll()
        val currentDistance = distances[current.x][current.y]

        if (currentDistance > minDistance) break

        passengers.forEach { passenger ->
            if (passenger.start == current) {
                if (currentDistance < minDistance) {
                    nearestPassengers.clear()
                    minDistance = currentDistance
                }
                if (currentDistance == minDistance) {
                    nearestPassengers.add(passenger)
                }
            }
        }

        for (i in 0..3) {
            val nx = current.x + dx[i]
            val ny = current.y + dy[i]

            if (nx in matrix.indices && ny in matrix.indices && !visited[nx][ny] && matrix[nx][ny] != 1) {
                visited[nx][ny] = true
                queue.offer(Point(nx, ny))
                distances[nx][ny] = currentDistance + 1
            }
        }
    }

    val nearestPassenger = nearestPassengers.minWithOrNull(
        compareBy<Passenger> { it.start.x }
            .thenBy { it.start.y }
    )

    return Pair(nearestPassenger, if (nearestPassenger != null) minDistance else -1)
}

fun bfs(matrix: Array<IntArray>, taxi: Point, destination: Point): Int {
    val queue = LinkedList<Point>()
    val visited = Array(matrix.size) { BooleanArray(matrix[0].size) { false } }
    val distances = Array(matrix.size) { IntArray(matrix[0].size) { -1 } }

    queue.offer(taxi)
    visited[taxi.x][taxi.y] = true
    distances[taxi.x][taxi.y] = 0

    while (queue.isNotEmpty()) {
        val current = queue.poll()

        if (current == destination) return distances[current.x][current.y]

        for (i in 0..3) {
            val nx = current.x + dx[i]
            val ny = current.y + dy[i]

            if (nx in matrix.indices && ny in matrix.indices && !visited[nx][ny] && matrix[nx][ny] != 1) {
                visited[nx][ny] = true
                queue.offer(Point(nx, ny))
                distances[nx][ny] = distances[current.x][current.y] + 1
            }
        }
    }

    return -1
}