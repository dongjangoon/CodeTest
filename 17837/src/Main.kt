data class ChessPiece(val id: Int, val direction: Int)
data class Point(val x: Int, val y: Int)

val dx = intArrayOf(0, 0, 0, -1, 1)
val dy = intArrayOf(0, 1, -1, 0, 0)

fun main() {
    val (n, k) = readln().split(" ").map { it.toInt() }
    val colorBoard = Array(n) { readln().split(" ").map { it.toInt() }.toIntArray() }
    val chessBoard = Array(n) { Array(n) { mutableListOf<ChessPiece>() } }
    val chessPieces = Array(k + 1) { Point(0,0) }

    for (i in 1..k) {
        val (row, col, dir) = readln().split(" ").map { it.toInt() }
        chessBoard[row-1][col-1].add(ChessPiece(i, dir))
        chessPieces[i] = Point(row-1, col-1)
    }

    var turn = 1

    while (turn <= 1000) {
        for (i in 1..k) {
            val (x, y) = chessPieces[i]

            // 인덱스 확인
            val idx = chessBoard[x][y].indexOfFirst { it.id == i }

            if (idx != -1) {
                val piece = chessBoard[x][y][idx]
                var direction = chessBoard[x][y][idx].direction

                var nx = x + dx[direction]
                var ny = y + dy[direction]

                when {
                    nx !in 0 until n || ny !in 0 until n || colorBoard[nx][ny] == 2 -> {
                        // 격자 밖이나 파란색
                        direction = when (direction) {
                            1 -> 2
                            2 -> 1
                            3 -> 4
                            4 -> 3
                            else -> direction
                        }
                        chessBoard[x][y][idx] = piece.copy(direction = direction)
                        nx = x + dx[direction]
                        ny = y + dy[direction]

                        // 방향만 바꾸고 이동은 하지 않음
                        if (nx !in 0 until n || ny !in 0 until n || colorBoard[nx][ny] == 2) continue
                    }
                }

                // 위에 쌓인 말들 포함해서 서브리스트로 분리
                val movingPieces = chessBoard[x][y].subList(idx, chessBoard[x][y].size).toMutableList()

                when (colorBoard[nx][ny]) {
                    0 -> chessBoard[nx][ny].addAll(movingPieces)
                    1 -> chessBoard[nx][ny].addAll(movingPieces.reversed())
                }

                // 원래 위치에서 말 제거
                repeat(movingPieces.size) { chessBoard[x][y].removeAt(idx) }

                // 말의 새 위치 업데이트
                movingPieces.forEach { chessPieces[it.id] = Point(nx, ny) }

                // 게임 종료 조건
                if (chessBoard[nx][ny].size >= 4) {
                    println(turn)
                    return
                }
            }
        }

        turn++
    }

    println(-1)
}