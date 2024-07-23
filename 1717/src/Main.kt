//fun main() {
//    val (n, m) = readln().split(" ").map { it.toInt() }
//    val parents = IntArray(n+1) { it }
//
//    repeat(m) {
//        val (calculation, a, b) = readln().split(" ").map { it.toInt() }
//        if (calculation == 1) {
//            if (find(parents, a) == find(parents, b)) {
//                println("YES")
//            } else {
//                println("NO")
//            }
//        } else {
//            union(parents, a, b)
//        }
//    }
//}
//
//fun find(parents: IntArray, x: Int): Int {
//    if (parents[x] != x) {
//        parents[x] = find(parents, parents[x])
//    }
//    return parents[x]
//}
//
//fun union(parents: IntArray, a: Int, b: Int) {
//    val rootA = find(parents, a)
//    val rootB = find(parents, b)
//    if (rootA != rootB) {
//        parents[rootB] = rootA
//    }
//}

class DisjointSet(size: Int) {
    private val parent = IntArray(size) { it }

    fun find(x: Int): Int {
        if (parent[x] != x) {
            parent[x] = find(parent[x])
        }
        return parent[x]
    }

    fun union(a: Int, b: Int) {
        val rootA = find(a)
        val rootB = find(b)
        if (rootA != rootB) {
            parent[rootB] = rootA
        }
    }

    fun areConnected(a: Int, b: Int): Boolean = find(a) == find(b)
}

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val disjointSet = DisjointSet(n + 1)

    repeat(m) {
        val (operation, a, b) = readln().split(" ").map { it.toInt() }
        when (operation) {
            0 -> disjointSet.union(a, b)
            1 -> println(if (disjointSet.areConnected(a, b)) "YES" else "NO")
        }
    }
}