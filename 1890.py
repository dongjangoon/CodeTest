import sys

input = sys.stdin.readline
n = int(input())
arr = [list(map(int, input().split())) for _ in range(n)]
d = [[-1]*n for _ in range(n)]

def dp(i, j):
    if i == n-1 and j == n-1:
        return 1
    if d[i][j] != -1:
        return d[i][j]
    if d[i][j] == -1:
        d[i][j] = 0

    x1, y1 = i+arr[i][j], j
    x2, y2 = i, j+arr[i][j]

    if 0 <= x1 < n and 0 <= y1 < n:
        d[i][j] += dp(x1, y1)
    if 0 <= x2 < n and 0 <= y2 < n:
        d[i][j] += dp(x2, y2)

    return d[i][j]

print(dp(0, 0))

