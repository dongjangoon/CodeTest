fun main() {
    val (n ,m) = readln().split(" ").map { it.toInt() }
    val numbers = readln().split(" ").map { it.toInt() }.sorted()
    val used = BooleanArray(n)
    val result = IntArray(m)
    val set = mutableSetOf<String>()

    fun dfs(depth: Int) {
        if (depth == m) {
            val sequence = result.joinToString(" ")
            if (sequence !in set) {
                println(sequence)
                set.add(sequence)
            }
            return
        }

        for (i in numbers.indices) {
            if (!used[i]) {
                used[i] = true
                result[depth] = numbers[i]
                dfs(depth + 1)
                used[i] = false
            }
        }
    }

    dfs(0)
}