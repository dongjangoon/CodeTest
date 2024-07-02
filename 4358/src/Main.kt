fun main() {
    val treeMap = mutableMapOf<String, Int>()
    var totalTrees = 0

    while (true) {
        val line = readlnOrNull() ?: break
        treeMap[line] = treeMap.getOrDefault(line, 0) + 1
        totalTrees++
    }

    treeMap.toSortedMap().forEach { (tree, count) ->
        val percentage = count.toDouble() / totalTrees * 100
        println("$tree ${String.format("%.4f", percentage)}")
    }
}