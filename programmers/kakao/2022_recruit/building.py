def solution(board, skill):
    # 누적합
    r, c = len(board), len(board[0])
    delta = [[0] * (c+1) for _ in range(r+1)]

    for _type, r1, c1, r2, c2, degree in skill:
        degree = -degree if _type == 1 else degree

        delta[r1][c1] += degree
        delta[r1][c2+1] -= degree
        delta[r2+1][c1] -= degree
        delta[r2+1][c2+1] += degree

    # 행 기준
    for i in range(r):
        for j in range(1, c):
            delta[i][j] += delta[i][j-1]

    # 열 기준
    for j in range(c):
        for i in range(1, r):
            delta[i][j] += delta[i-1][j]

    return sum(board[x][y] + delta[x][y] > 0 for y in range(c) for x in range(r))