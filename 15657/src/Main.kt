fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val numbers = readln().split(" ").map { it.toInt() }.sorted()
    val result = IntArray(m)

    fun dfs(depth: Int) {
        if (depth == m) {
            println(result.joinToString(" "))
            return
        }

        for (num in numbers) {
            if (depth == 0 || result[depth - 1] <= num) {
                result[depth] = num
                dfs(depth + 1)
            }
        }
    }

    dfs(0)
}