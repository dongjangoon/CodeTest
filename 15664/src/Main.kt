fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val numbers = readln().split(" ").map { it.toInt() }.sorted()
    val result = IntArray(m)
    val used = BooleanArray(n)

    fun dfs(start: Int, depth: Int) {
        if (depth == m) {
            println( result.joinToString(" "))
            return
        }

        var prev = 0
        for (i in start until n) {
            if (!used[i] && prev != numbers[i]) {
                used[i] = true
                result[depth] = numbers[i]
                dfs(i + 1, depth + 1)
                used[i] = false
                prev = numbers[i]
            }
        }
    }

    dfs(0, 0)
}