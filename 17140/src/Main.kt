import kotlin.math.max

fun main() {
    val (r, c, k) = readln().split(" ").map { it.toInt() }
    var matrix = mutableListOf<MutableList<Int>>()

    repeat (3) {
        matrix.add(readln().split(" ").map { it.toInt() }.toMutableList())
    }

    var time = 0

    while (time <= 100) {
        if (r-1 < matrix.size && c-1 < matrix[0].size && matrix[r-1][c-1] == k) {
            println(time)
            return
        }

        if (matrix[0].size > matrix.size) {
            matrix = c(matrix)
        } else {
            matrix = r(matrix)
        }

        time++
    }

    println(-1)
}

fun r(matrix: MutableList<MutableList<Int>>): MutableList<MutableList<Int>> {
    val result = mutableListOf<MutableList<Int>>()
    var maxLength = 0

    for (row in matrix) {
        val map = row.filter { it != 0 }.groupBy { it }.mapValues { it.value.size }
        val sortedList = map.entries.sortedWith(compareBy<Map.Entry<Int, Int>> { it.value }.thenBy { it.key })
            .flatMap { listOf(it.key, it.value) }
            .take(100)

        result.add(sortedList.toMutableList())
        maxLength = max(maxLength, sortedList.size)
    }

    result.forEach { it.addAll(List(maxLength - it.size) { 0 }) }
    return result
}

fun c(matrix: MutableList<MutableList<Int>>): MutableList<MutableList<Int>> {
    val transposed = MutableList(matrix[0].size) { i -> matrix.map { it[i] }.toMutableList() }
    val result = r(transposed)
    return MutableList(result[0].size) { i ->
        result.map { it[i] }.toMutableList()
    }
}