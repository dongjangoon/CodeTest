fun main() {
    val (n, w, l) = readln().split(" ").map { it.toInt() }
    val truckWeights = readln().split(" ").map { it.toInt() }

    println(calculateCrossingTime(w, l, truckWeights))
}

fun calculateCrossingTime(bridgeLength: Int, weightLimit: Int, truckWeights: List<Int>): Int {
    var time = 0
    val bridge = ArrayDeque<Int>()
    var currentWeight = 0
    var truckIndex = 0

    repeat(bridgeLength) { bridge.addLast(0) }

    while (truckIndex < truckWeights.size || currentWeight > 0) {
        currentWeight -= bridge.removeFirst()
        time++

        if (truckIndex < truckWeights.size) {
            if (currentWeight + truckWeights[truckIndex] <= weightLimit) {
                bridge.addLast(truckWeights[truckIndex])
                currentWeight += truckWeights[truckIndex]
                truckIndex++
            } else {
                bridge.addLast(0)
            }
        } else {
            bridge.addLast(0)
        }
    }

    return time
}