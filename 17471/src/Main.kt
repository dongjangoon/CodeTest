import java.util.LinkedList
import kotlin.math.abs

data class PairList(val x: List<Int>, val y: List<Int>)

fun main() {
    val n = readln().toInt()
    val population = readln().split(" ").map { it.toInt() }
    var minDiff = -1

    val map = mutableMapOf<Int, MutableList<Int>>()
    for (i in 1..n) {
        map[i] = mutableListOf()
    }

    for (i in 1..n) {
        val areaInfo = readln().split(" ").map { it.toInt() }
        val num = areaInfo[0]
        for (j in 1..num) {
            map[i]?.add(areaInfo[j])
        }
    }

    val combinations = generateCombinations(n)
    for (comb in combinations) {
        if (connected(n, comb.x, map) && connected(n, comb.y, map)) {
            val diff = getDiff(population, comb.x, comb.y)
            minDiff = if (minDiff == -1) diff else minOf(minDiff, diff)
        }
    }

    println(minDiff)
}

fun generateCombinations(n: Int): List<PairList> {
    val result = mutableListOf<PairList>()
    val current = mutableListOf<Int>()

    fun backtrack(index: Int) {
        if (index > n) {
            val group1 = current.toList()
            val group2 = (1..n).filter { it !in group1 }
            if (group1.isNotEmpty() && group2.isNotEmpty()) {
                result.add(PairList(group1, group2))
            }
            return
        }

        // index를 첫 번째 그룹에 포함하지 않는 경우
        backtrack(index + 1)

        // index를 첫 번째 그룹에 포함하는 경우
        current.add(index)
        backtrack(index + 1)
        current.removeAt(current.lastIndex)

    }

    backtrack(1)
    return result
}

fun connected(n: Int, group: List<Int>, map: MutableMap<Int, MutableList<Int>>): Boolean {
    val queue = LinkedList<Int>()
    val visited = BooleanArray(n+1)

    queue.add(group[0])
    visited[group[0]] = true

    while (queue.isNotEmpty()) {
        val x = queue.pop()

        for (j in map[x]!!) {
            if (!visited[j] && j in group) {
                queue.add(j)
                visited[j] = true
            }
        }
    }

    for (i in group) {
        if (!visited[i]) return false
    }

    return true
}

fun getDiff(population: List<Int>, x: List<Int>, y: List<Int>): Int {
    var populationX = 0
    var populationY = 0

    for (i in x) {
        populationX += population[i-1]
    }

    for (i in y) {
        populationY += population[i-1]
    }

    return abs(populationX - populationY)
}