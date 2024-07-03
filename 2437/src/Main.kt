fun main() {
    val n = readln().toInt()
    val weights = readln().split(" ").map { it.toInt() }
    val sortedWeights = weights.sorted()

    var sum = 0

    for (i in 0 .. n-1) {
        if (sum + 1 < sortedWeights[i]) {
            continue
        } else {
            sum += sortedWeights[i]
        }
    }

    println(sum + 1)
}