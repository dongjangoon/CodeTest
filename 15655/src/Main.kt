fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val numbers = readln().split(" ").map { it.toInt() }.sorted()
    solve(numbers, n, m)
}

fun solve(numbers: List<Int>, n: Int, m: Int) {
    fun dfs(idx: Int, current: MutableList<Int>) {
        if (current.size == m) {
            println(current.joinToString(" "))
            return
        }

        for (i in idx until n) {
            current.add(numbers[i])
            dfs(i + 1, current)
            current.removeLast()
        }
    }

    dfs(0, mutableListOf())
}