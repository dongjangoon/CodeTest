fun main() {
    val n = readln().toInt()
    val population = Array(n) { readln().split(" ").map { it.toInt() }.toIntArray() }

    var minDiff = Int.MAX_VALUE

    for (x in 0 ..< n) {
        for (y in 0 ..< n) {
            for (d1 in 1 ..< n) {
                for (d2 in 1 ..< n) {
                    if (x + d1 + d2 >= n || y - d1 < 0 || y + d2 >= n) continue
                    minDiff = minOf(minDiff, getDifference(n, population, x, y, d1, d2))
                }
            }
        }
    }

    println(minDiff)
}

fun getDifference(n: Int, population: Array<IntArray>, x: Int, y: Int, d1: Int, d2: Int): Int {
    val district = Array(n) { IntArray(n) }
    val populations = IntArray(5)

    // 경계선 그리기
    for (i in 0..d1) {
        district[x+i][y-i] = 5
        district[x+d2+i][y+d2-i] = 5
    }
    for (i in 0..d2) {
        district[x+i][y+i] = 5
        district[x+d1+i][y-d1+i] = 5
    }

    // 1번 선거구
    for (r in 0 ..< x+d1) {
        for (c in 0..y) {
            if (district[r][c] == 5) break
            populations[0] += population[r][c]
        }
    }

    // 2번 선거구
    for (r in 0 .. x+d2) {
        for (c in n-1 downTo y+1) {
            if (district[r][c] == 5) break
            populations[1] += population[r][c]
        }
    }

    // 3번 선거구
    for (r in x+d1 ..< n) {
        for (c in 0 ..< y-d1+d2) {
            if (district[r][c] == 5) break
            populations[2] += population[r][c]
        }
    }

    // 4번 선거구
    for (r in x+d2+1 ..< n) {
        for (c in n-1 downTo y-d1+d2) {
            if (district[r][c] == 5) break
            populations[3] += population[r][c]
        }
    }

    // 5번 선거구
    populations[4] = population.sumOf { it.sum() } - populations.sum()

    return populations.maxOrNull()!! - populations.minOrNull()!!
}