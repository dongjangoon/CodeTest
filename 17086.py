from collections import deque

n, m = map(int, input().split())
arr = []
shark = deque()

for i in range(n):
    temp = list(map(int, input().split()))
    for j in range(m):
        if temp[j] == 1:
            shark.append((i, j))  # 상어 전부 캐치
    arr.append(temp)

dx = [-1, 1, 0, 0, -1, -1, 1, 1]
dy = [0, 0, 1, -1, 1, -1, 1, -1]

def bfs():
    while shark:
        x, y = shark.popleft()

        for k in range(8):
            new_x = x + dx[k]
            new_y = y + dy[k]

            if new_y < 0 or new_x < 0 or new_y >= m or new_x >= n:
                continue

            if arr[new_x][new_y] == 0:
                shark.append((new_x, new_y))
                arr[new_x][new_y] = arr[x][y] + 1

    return



bfs()
ans = 0
for i in range(n):
    for j in range(m):
        ans = max(ans, arr[i][j]-1)


print(ans)
