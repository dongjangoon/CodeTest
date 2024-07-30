fun main() {
    val n = readln().toInt()
    val ropes = mutableListOf<Int>()
    repeat(n) {
        ropes.add(readln().toInt())
    }

    ropes.sort()

    var maxWeight = 0
    for (i in ropes.indices) {
        val k = n - i
        maxWeight = maxOf(maxWeight, ropes[i] * k)
    }

    println(maxWeight)
}