fun main() {
    val n = readln().toInt()
    val innings = Array(n) { readln().split(" ").map { it.toInt() }.toIntArray() }

    var maxScore = 0

    val permutations = generatePermutations()

    for (perm in permutations) {
        val score = simulate(innings, perm)
        maxScore = maxOf(score, maxScore)
    }

    println(maxScore)
}

fun generatePermutations(): List<List<Int>> {
    val players = (1..8).toList()
    val result = mutableListOf<MutableList<Int>>()

    fun backtrack(current: MutableList<Int>) {
        if (current.size == 8) {
            val fullOrder = current.toMutableList()
            fullOrder.add(3, 0)
            result.add(fullOrder)
            return
        }

        for (player in players) {
            if (player !in current) {
                current.add(player)
                backtrack(current)
                current.removeAt(current.lastIndex)
            }
        }
    }

    backtrack(mutableListOf())
    return result
}

fun simulate(innings: Array<IntArray>, perm: List<Int>): Int {
    var totalScore = 0
    var batterIndex = 0

    for (inning in innings) {
        var bases = 0
        var outCount = 0
        var score = 0

        while (outCount < 3) {
            val currentBatter = perm[batterIndex]
            val hit = inning[currentBatter]

            when (hit) {
                0 -> outCount++
                else -> {
                    score += processHit(hit, bases)
                    bases = updateBases(hit, bases)
                }
            }

            batterIndex = (batterIndex + 1) % 9
        }

        totalScore += score
    }

    return totalScore
}

fun processHit(hit: Int, bases: Int): Int {
    var score = 0
    var newBases = bases

    for (i in 3 downTo 1) { // 3, 2, 1 루 순으로 확인
        if (newBases and (1 shl i) != 0) { // 각 루에 주자가 있으면
            if (i + hit > 3) { // 득점
                score++
            } else {
                newBases = newBases or (1 shl (i + hit)) // 주자 새 위치로 이동
            }
            newBases = newBases and (1 shl i).inv() // 원래 위치 주자 제거
        }
    }

    if (hit == 4) score++
    return score
}

fun updateBases(hit: Int, bases: Int): Int {
    var newBases = bases
    newBases = newBases shl hit // 주자 진루
    newBases = newBases or (1 shl (hit)) // 타자 위치
    return newBases and 0b1111 // 4루 이상의 비트 제거
}