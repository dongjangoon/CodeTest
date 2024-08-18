fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val numbers = readln().split(" ").map { it.toInt() }.sorted()
    val result = IntArray(m)

    fun dfs(start: Int, depth: Int) {
        if (depth == m) {
            println(result.joinToString(" "))
            return
        }

        var prev = -1
        for (i in start until n) {
            if (prev != numbers[i]) {
                result[depth] = numbers[i]
                dfs(i, depth + 1)
                prev = numbers[i]
            }
        }
    }

    dfs(0, 0)
}