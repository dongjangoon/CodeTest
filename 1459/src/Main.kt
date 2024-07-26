fun main() {
    val (x, y, w, s) = readln().split(" ").map { it.toLong() }

    val maxXY = maxOf(x, y)
    val minXY = minOf(x, y)

    // 1. 대각선으로만 이동 (짝수만 가능)
    val diagonalOnly = if ((x + y) % 2 == 0L) maxXY * s else (maxXY - 1) * s + w

    // 2. 대각선 + 직선
    val diagonalAndStraight = minXY * s + (maxXY - minXY) * w

    // 3. 직선으로만 이동
    val straightOnly = (x + y) * w

    val result = minOf(diagonalOnly, diagonalAndStraight, straightOnly)

    println(result)
}