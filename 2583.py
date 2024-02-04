from collections import deque

m, n, k = map(int, input().split())
check = [[0]*(n) for _ in range(m)]
answer = []

for _ in range(k):
    ax, ay, bx, by = map(int, input().split())
    for i in range(ay, by):
        for j in range(ax, bx):
            check[i][j] = 1

dx, dy = [0, -1, 0, 1], [-1, 0, 1, 0]

for i in range(m):
    for j in range(n):
        if check[i][j] == 0:
            queue = deque()
            queue.append((i, j))
            check[i][j] = 1
            area = 1

            while(len(queue) > 0):
                y, x = queue.popleft()
                check[y][x] = 1
                for k in range(4):
                    nx, ny = x+dx[k], y+dy[k]
                    if nx < 0 or nx >= n or ny < 0 or ny >= m or check[ny][nx]:
                        continue
                    else:
                        check[ny][nx] = 1
                        queue.append((ny, nx))
                        area += 1
            answer.append(area)

print(len(answer))
answer.sort()
for i in answer:
    print(i, end=' ')

