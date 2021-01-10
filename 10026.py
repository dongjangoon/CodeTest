from collections import deque

dx = [0,0,-1,1]
dy = [1,-1,0,0]

n = int(input())

def bfs(x, y):
    q = deque()
    q.append((x, y))
    check[x][y] = True

    while q:
        x, y = q.popleft()
        for k in range(4):
            nx, ny = x+dx[k], y+dy[k]
            if nx < 0 or nx >= n or ny < 0 or ny >= n or check[nx][ny]:
                continue
            if a[nx][ny] == a[x][y]:
                check[nx][ny] = True
                q.append((nx, ny))

a = [input() for _ in range(n)]
check = [[False]*n for _ in range(n)]
area = 0
rgarea = 0

for i in range(n):
    for j in range(n):
        if not check[i][j]:
            area += 1
            bfs(i, j)

for i in range(n):
    a[i] = a[i].replace('G', 'R')

check = [[False]*n for _ in range(n)]

for i in range(n):
    for j in range(n):
        if not check[i][j]:
            rgarea += 1
            bfs(i, j)

print(str(area) + " " + str(rgarea))

