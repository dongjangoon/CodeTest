from collections import deque

dx = [0, 0, -1, 1]
dy = [1, -1, 0, 0]

n, m, wall = map(int, input().split())
a = []
for i in range(n):
    a.append(list(map(int, list(input()))))
dist = [[[0]*11 for i in range(m)] for j in range(n)]
q = deque()
q.append((0, 0, 0))
dist[0][0][0] = 1

while q:
    x, y, cnt = q.popleft()
    for k in range(4):
        nx, ny = x+dx[k], y+dy[k]
        if nx < 0 or nx >= n or ny < 0 or ny >= m:
            continue
        if a[nx][ny] == 0 and dist[nx][ny][cnt] == 0:
            dist[nx][ny][cnt] = dist[x][y][cnt] + 1
            q.append((nx, ny, cnt))
        if cnt+1 <= wall and a[nx][ny] == 1 and dist[nx][ny][cnt+1] == 0:
            dist[nx][ny][cnt+1] = dist[x][y][cnt] + 1
            q.append((nx, ny, cnt+1))

ans = -1
for i in range(wall+1):
    if not dist[n-1][m-1][i] == 0:
        if ans == -1 or dist[n-1][m-1][i] < ans:
            ans = dist[n-1][m-1][i]

print(ans)