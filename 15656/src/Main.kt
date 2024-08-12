import java.io.BufferedWriter
import java.io.OutputStreamWriter

fun main() {
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val (n, m) = readln().split(" ").map { it.toInt() }
    val numbers = readln().split(" ").map { it.toInt() }.sorted()
    val result = IntArray(m)

    fun dfs(depth: Int) {
        if (depth == m) {
            bw.write(result.joinToString(" "))
            bw.newLine()
            return
        }

        for (num in numbers) {
            result[depth] = num
            dfs(depth + 1)
        }
    }

    dfs(0)
    bw.flush()
    bw.close()
}