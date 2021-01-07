from collections import deque
dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]

n, m = map(int, input().split())
a = [list(input()) for _ in range(n)]
c = [[False]*m for _ in range(n)]
q = deque()

def simulate(sx, sy):
    if c[sx][sy]:
        return (0, 0)
    sheepCnt = 0
    wolfCnt = 0
    if a[sx][sy] == 'o':
        sheepCnt += 1
    else:
        wolfCnt += 1
    q = deque()
    q.append((sx, sy))
    c[sx][sy] = True

    while q:
        x, y = q.popleft()
        for k in range(4):
            nx, ny = x+dx[k], y+dy[k]
            if nx < 0 or nx >= n or ny < 0 or ny >= m or a[nx][ny] =='#' or c[nx][ny]:
                continue
            if a[nx][ny] == 'o':
                sheepCnt += 1
            if a[nx][ny] == 'v':
                wolfCnt += 1
            c[nx][ny] = True
            q.append((nx, ny))
    if sheepCnt > wolfCnt:
        return (sheepCnt, 0)
    else:
        return (0, wolfCnt)

sheep = []
wolf = []

for i in range(n):
    for j in range(m):
        if a[i][j] == 'o':
            sheep.append((i, j))
        if a[i][j] == 'v':
            wolf.append((i, j))

sheepAns = 0
wolfAns = 0

for i in range(len(sheep)):
    x, y = sheep[i]
    sheepCnt, wolfCnt = simulate(x, y)
    sheepAns += sheepCnt
    wolfAns += wolfCnt
for i in range(len(wolf)):
    x, y = wolf[i]
    sheepCnt, wolfCnt = simulate(x, y)
    sheepAns += sheepCnt
    wolfAns += wolfCnt

print(str(sheepAns) + ' ' + str(wolfAns))



