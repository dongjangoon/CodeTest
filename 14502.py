from collections import deque
n, m = map(int, input().split())
a = [list(map(int, input().split())) for _ in range(n)]
dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]

def bfs(a):
    n = len(a)
    m = len(a[0])
    b = [[0]*m for _ in range(n)]
    q = deque()
    for x in range(n):
        for y in range(m):
            b[x][y] = a[x][y]
            if b[x][y] == 2:
                q.append((x, y))
    
    while q:
        x, y = q.popleft()
        for k in range(4):
            nx = x + dx[k]
            ny = y + dy[k]
            if nx >= 0 and nx < n and ny >= 0 and ny < m and b[nx][ny] == 0:
                b[nx][ny] = 2
                q.append((nx, ny))

    cnt = 0
    for x in range(n):
        for y in range(m):
            if b[x][y] == 0:
                cnt += 1
    return cnt


ans = 0
for x1 in range(n):
    for y1 in range(m):
        if a[x1][y1] != 0:
            continue
        for x2 in range(n):
            for y2 in range(m):
                if a[x2][y2] != 0:
                    continue
                for x3 in range(n):
                    for y3 in range(m):
                        if a[x3][y3] != 0:
                            continue
                        if x1 == x2 and y1 == y2:
                            continue
                        if x2 == x3 and y2 == y3:
                            continue
                        if x1 == x3 and y1 == y3:
                            continue
                        a[x1][y1] = 1
                        a[x2][y2] = 1
                        a[x3][y3] = 1
                        cur = bfs(a)
                        if ans < cur:
                            ans = cur 
                        a[x1][y1] = 0
                        a[x2][y2] = 0
                        a[x3][y3] = 0
print(ans)