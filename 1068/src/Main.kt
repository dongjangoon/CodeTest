fun main() {
    val n = readln().toInt()
    val parents = readln().split(" ").map { it.toInt() }
    val deleteNode = readln().toInt()

    val tree = List(n) { mutableListOf<Int>() }
    var root = -1

    // 트리 구성
    for (i in 0 until n) {
        if (parents[i] == -1) {
            root = i
        } else {
            tree[parents[i]].add(i)
        }
    }

    // 노드 삭제 및 리프 노드 계산
    fun dfs(node: Int): Int {
        if (node == deleteNode) return 0
        if (tree[node].isEmpty() || tree[node].size == 1 && tree[node][0] == deleteNode) return 1

        var leafCount = 0
        for (child in tree[node]) {
            leafCount += dfs(child)
        }
        return if (leafCount == 0) 1 else leafCount
    }

    val result = if (root == deleteNode) 0 else dfs(root)
    println(result)
}