import java.util.LinkedList

val dx = arrayOf(0, 1, -1, 0)
val dy = arrayOf(1, 0, 0, -1)

data class Pair(val x: Int, val y: Int)
data class Bridge(val from: Int, val to: Int, val length: Int)

class UnionFind(size: Int) {
    private val parent = IntArray(size) { it }

    fun find(x: Int): Int {
        if (parent[x] != x) parent[x] = find(parent[x])
        return parent[x]
    }

    fun union(x: Int, y: Int) {
        val rootX = find(x)
        val rootY = find(y)
        if (rootX != rootY) parent[rootY] = rootX
    }
}

fun kruskal(n: Int, bridges: List<Bridge>): Int {
    val uf = UnionFind(n + 1)
    var totalLength = 0
    var connectedIslands = 0

    for (bridge in bridges.sortedBy { it.length }) {
        if (uf.find(bridge.from) != uf.find(bridge.to)) {
            uf.union(bridge.from, bridge.to)
            totalLength += bridge.length
            connectedIslands++
        }
    }

    return if (connectedIslands == n - 1) totalLength else -1
}

fun main() {
    // 다리 길이 2이상
    // 가로 또는 세로
    val (n, m) = readln().split(" ").map { it.toInt() }
    val matrix = Array(n) { readln().split(" ").map { it.toInt() }.toIntArray() }

    // 섬에 번호 부여
    val islandMap = identifyIslands(n, m, matrix)

    // 가능한 모든 다리 생성
    val bridges = makeBridges(n, m, matrix, islandMap)
    val islandCount = islandMap.size

    val result = kruskal(islandCount, bridges)
    println(result)
}

fun identifyIslands(n: Int, m: Int, matrix: Array<IntArray>): Map<Int, List<Pair>> {
    val result = mutableMapOf<Int, MutableList<Pair>>()
    var count = 1
    val visited = Array(n) { BooleanArray(m) }

    for (i in 0 ..< n) {
        for (j in 0 ..< m) {
            if (visited[i][j]) continue

            if (matrix[i][j] == 0) {
                visited[i][j] = true
                continue
            }

            if (matrix[i][j] == 1) {
                val queue = LinkedList<Pair>()
                queue.offer(Pair(i, j))
                visited[i][j] = true
                matrix[i][j] = count
                result[count] = mutableListOf(Pair(i, j))

                while (queue.isNotEmpty()) {
                    val (x, y) = queue.pop()

                    for (k in 0 ..< 4) {
                        val nx = x + dx[k]
                        val ny = y + dy[k]

                        if (nx in 0..< n && ny in 0 ..< m && matrix[nx][ny] == 1 && !visited[nx][ny]) {
                            matrix[nx][ny] = count
                            queue.offer(Pair(nx, ny))
                            visited[nx][ny] = true
                            result[count]!!.add(Pair(nx, ny))
                        }
                    }
                }

                count++
            }
        }
    }

    return result
}

fun makeBridges(n: Int, m: Int, matrix: Array<IntArray>, islandMap: Map<Int, List<Pair>>): List<Bridge> {
    val result = mutableMapOf<Pair, Int>()

    for ((islandNum, coordinates) in islandMap) {
        for ((x, y) in coordinates) {
            for (dir in 0 ..< 4) {
                var nx = x + dx[dir]
                var ny = y + dy[dir]
                var length = 0

                while (nx in 0 ..< n && ny in 0 ..< m) {
                    if (matrix[nx][ny] == islandNum) break // 같은 섬
                    if (matrix[nx][ny] != 0) {
                        if (length >= 2) {
                            val key = if (islandNum < matrix[nx][ny])
                                Pair(islandNum, matrix[nx][ny])
                            else
                                Pair(matrix[nx][ny], islandNum)
                            result[key] = minOf(result.getOrDefault(key, Int.MAX_VALUE), length) // 더 짧은 다리 선택
                        }
                        break
                    }
                    length++
                    nx += dx[dir]
                    ny += dy[dir]
                }
            }
        }
    }

    return result.map { Bridge(it.key.x, it.key.y, it.value) }
}