fun main() {
    val n = readln().toInt()
    val matrix = Array(n) { readln().split(" ").map { it.toInt() }.toIntArray() }

    // 3차원 dp: [행][열][파이프 방향]
    // 방향: 0 - 가로, 1 = 세로, 2 - 대각선
    val dp = Array(n) { Array(n) { IntArray(3) } }
    dp[0][1][0] = 1

    for (i in 0 until n) {
        for (j in 1 until n) {
            if (matrix[i][j] == 1) continue

            // 가로 방향으로 이동
            if (j + 1 < n && matrix[i][j + 1] == 0) {
                dp[i][j + 1][0] += dp[i][j][0] + dp[i][j][2]
            }

            // 세로 방향으로 이동
            if (i + 1 < n && matrix[i + 1][j] == 0) {
                dp[i + 1][j][1] += dp[i][j][1] + dp[i][j][2]
            }

            // 대각선 방향으로 이동
            if (i + 1 < n && j + 1 < n && matrix[i][j + 1] == 0 && matrix[i + 1][j] == 0 &&
                matrix[i + 1][j + 1] == 0) {
                dp[i + 1][j + 1][2] += dp[i][j][0] + dp[i][j][1] + dp[i][j][2]
            }
        }
    }

    println(dp[n-1][n-1].sum())
}