import java.io.*
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (n, r, q) = br.readLine().split(" ").map { it.toInt() }
    val tree = Array(n + 1) { mutableListOf<Int>() }
    val size = IntArray(n + 1)
    val visited = BooleanArray(n + 1)

    repeat(n - 1) {
        val (u, v) = br.readLine().split(" ").map { it.toInt() }
        tree[u].add(v)
        tree[v].add(u)
    }

    val stack = Stack<Pair<Int, Int>>()
    stack.push(Pair(r, -1))

    while (stack.isNotEmpty()) {
        val (current, parent) = stack.pop()

        if (visited[current]) {
            if (parent != -1) {
                size[parent] += size[current]
            }
            continue
        }

        visited[current] = true
        size[current] = 1
        stack.push(Pair(current, parent))

        for (child in tree[current]) {
            if (child != parent && !visited[child]) {
                stack.push(Pair(child, current))
            }
        }
    }

    repeat(q) {
        val u = br.readLine().toInt()
        bw.write("${size[u]}\n")
    }

    bw.flush()
    bw.close()
    br.close()
}