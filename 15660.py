n, m = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(n)]
check = [[0] * m for _ in range(n)]
dy, dx = [1, -1, 0, 0], [0, 0, 1, -1]
answer = 0

