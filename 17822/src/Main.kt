fun main() {
    val (n, m, t) = readln().split(" ").map { it.toInt() }
    val circles = Array(n) { readln().split(" ").map { it.toInt() }.toIntArray() } // 상, 우, 하, 좌

    repeat(t) {
        val (x, d, k) = readln().split(" ").map { it.toInt() }

        // 회전
        for (i in x-1 until n step x) {
            rotate(circles[i], d, k, m)
        }

        // 수 확인
        val checkList = mutableSetOf<Pair<Int, Int>>()
        var anyChecked = false
        var sum = 0.0
        var count = 0

        for (i in 0 until n) {
            for (j in 0 until m) {
                if (circles[i][j] == 0) continue

                sum += circles[i][j]
                count++

                // 인접한 수 확인
                if (circles[i][(j+1) % m] == circles[i][j]) {
                    checkList.add(Pair(i, j))
                    checkList.add(Pair(i, (j+1) % m))
                    anyChecked = true
                }
                if (circles[i][(j-1+m) % m] == circles[i][j]) {
                    checkList.add(Pair(i, j))
                    checkList.add(Pair(i, (j-1+m) % m))
                    anyChecked = true
                }

                if (i > 0 && circles[i-1][j] == circles[i][j]) {
                    checkList.add(Pair(i, j))
                    checkList.add(Pair(i-1, j))
                    anyChecked = true
                }
                if (i < n-1 && circles[i+1][j] == circles[i][j]) {
                    checkList.add(Pair(i, j))
                    checkList.add(Pair(i+1, j))
                    anyChecked = true
                }

            }
        }

        if (anyChecked) {
            for (pair in checkList) {
                circles[pair.first][pair.second] = 0
            }
        } else {
            val avg = sum / count
            for (i in 0 until n) {
                for (j in 0 until m) {
                    if (circles[i][j] == 0) continue
                    else if (circles[i][j] > avg) {
                        circles[i][j]--
                    } else if (circles[i][j] < avg) {
                        circles[i][j]++
                    }
                }
            }
        }
    }

    println(circles.sumOf { it.sum() })
}

fun rotate(arr: IntArray, d: Int, k: Int, m: Int) {
    val rotateAmount = if (d == 0) k else m - k
    val rotated = IntArray(m)
    for (i in arr.indices) {
        rotated[(i + rotateAmount) % m] = arr[i]
    }
    System.arraycopy(rotated, 0, arr, 0, m)
}
