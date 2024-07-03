val board = Array(10) { IntArray(10) }
val paperCounts = IntArray(6) { 5 }
var minPapers = Int.MAX_VALUE

fun main() {
    for (i in 0 ..< 10) {
        board[i] = readln().split(" ").map { it.toInt() }.toIntArray()
    }

    dfs(0, 0, 0)

    println(if (minPapers == Int.MAX_VALUE) -1 else minPapers)
}

fun dfs(x: Int, y: Int, count: Int) {
    if (count >= minPapers) return

    if (x == 10) {
        minPapers = minOf(count, minPapers)
        return
    }

    if (y == 10) {
        dfs(x + 1, 0, count)
        return
    }

    if (board[x][y] == 0) {
        dfs(x, y + 1, count)
        return
    }

    for (size in 5 downTo 1) {
        if (paperCounts[size] > 0 && canCover(x, y, size)) {
            cover(x, y, size, 0)
            paperCounts[size]--
            dfs(x, y + size, count + 1)
            cover(x, y, size, 1)
            paperCounts[size]++
        }
    }
}

fun canCover(x: Int, y: Int, size: Int): Boolean {
    if (x + size > 10 || y + size > 10) return false
    for (i in x ..< x + size) {
        for (j in y ..< y + size) {
            if (board[i][j] == 0) return false

        }
    }

    return true
}

fun cover(x: Int, y: Int, size: Int, value: Int) {
    for (i in x ..< x + size) {
        for (j in y ..< y + size) {
            board[i][j] = value
        }
    }
}