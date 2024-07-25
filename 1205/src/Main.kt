fun main() {
    val (n, taesu, p) = readln().split(" ").map { it.toInt() }

    if (n == 0) {
        println(1)
        return
    }

    val scores = readln().split(" ").map { it.toInt() }

    if (n == p && taesu <= scores.last()) {
        println(-1)
        return
    }

    var rank = 1
    var sameCount = 0

    for (i in 0 until n) {
        if (taesu > scores[i]) {
            println(rank)
            return
        } else if (taesu == scores[i]) {
            sameCount++
        } else {
            rank += sameCount + 1
            sameCount = 0
        }

        if (rank > p) {
            println(-1)
            return
        }
    }

    if (n < p) {
        println(rank)
    } else {
        println(-1)
    }
}