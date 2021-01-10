from collections import deque

dx = [0, 0, -1, 1, -2, -2, -1, -1, 1, 1, 2, 2]
dy = [1, -1, 0, 0, -1, 1, 2, -2, 2, -2, 1, -1]
dz = [0,0,0,0,1,1,1,1,1,1,1,1]

horse = int(input())
n, m = map(int, input().split())
a = [list(map(int, input().split())) for _ in range(m)]
cnt = [[[-1]*31 for i in range(n)] for j in range(m)]

q = deque()
q.append((0, 0, 0))
cnt[0][0][0] = 0

while q:
    x, y, z = q.popleft()
    for k in range(12):
        nx, ny, nz = x+dx[k], y+dy[k], z+dz[k]
        if nx < 0 or nx >= m or ny < 0 or ny >= n or a[nx][ny] == 1:
            continue
        if nz <= horse:
            if cnt[nx][ny][nz] == -1:
                cnt[nx][ny][nz] = cnt[x][y][z] + 1
                q.append((nx, ny, nz))

ans = -1
for i in range(horse+1):
    if cnt[m-1][n-1][i] == -1:
        continue
    if ans == -1 or ans > cnt[m-1][n-1][i]:
        ans = cnt[m-1][n-1][i]

print(ans)
