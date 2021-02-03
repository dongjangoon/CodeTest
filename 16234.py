from collections import deque

n, l, r = map(int, input().split())
a = [list(map(int, input().split())) for _ in range(n)]
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

ans = 0

def simulate(x, y):
    union = list()
    union.append((x, y))
    q = deque()
    q.append((x,y))

    while q:
        x, y = q.popleft()

        for k in range(4):
            nx = x + dx[k]
            ny = y + dy[k]

            if 0 <= nx < n and 0 <= ny < n and not check[nx][ny]:
                if l <= abs(a[x][y] - a[nx][ny]) <= r:
                    q.append((nx, ny))
                    check[nx][ny] = True
                    union.append((nx, ny))
    return union

while True:
    check = [[False]*n for _ in range(n)]
    isTrue = False
    for i in range(n):
        for j in range(n):
            if not check[i][j]:
                check[i][j] = True
                union = simulate(i, j)
                if len(union) >= 2:
                    isTrue = True
                    avg = sum([a[x][y] for x, y in union]) // len(union)
                    for x, y in union:
                        a[x][y] = avg
    
    if not isTrue:
        break
    ans += 1
                
print(ans)