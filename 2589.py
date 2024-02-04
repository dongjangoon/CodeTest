from collections import deque

n, m = map(int, input().split())
arr = [input() for _ in range(n)]
dx, dy = [1, -1, 0, 0], [0, 0, 1, -1]
answer = 0

for i in range(n):
  for j in range(m):
    if arr[i][j] == 'W':
      continue

    visited = [[0] * m for _ in range(n)]
    q = deque()
    q.append((i, j, 0))
    visited[i][j] = 1

    while q:
      y, x, count = q.popleft()
      if count > answer:
        answer = count

      for k in range(4):
        nx, ny = x + dx[k], y + dy[k]
        if 0 <= nx < m and 0 <= ny < n and visited[ny][nx] == 0 and arr[ny][nx] == 'L':
          q.append((ny, nx, count + 1))
          visited[ny][nx] = 1

print(answer)
