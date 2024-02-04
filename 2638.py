from collections import deque

n, m = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(n)]
dx, dy = [1, -1, 0, 0], [0, 0, 1, -1]
answer = 0

while True:
  visited = [[0] * m for _ in range(n)]
  q = deque()
  visited[0][0] = 1
  q.append((0, 0))
  while q:
    y, x = q.popleft()
    for k in range(4):
      ny, nx = y + dy[k], x + dx[k]
      if 0 <= ny < n and 0 <= nx < m and visited[ny][nx] == 0:
        if arr[ny][nx]:
          arr[ny][nx] += 1
        else:
          visited[ny][nx] = 1
          q.append((ny, nx))

  flag = 0
  for i in range(n):
    for j in range(m):
      if arr[i][j] >= 3:
        arr[i][j] = 0
      elif 0 < arr[i][j]:
        flag = 1
        arr[i][j] = 1
  
  answer += 1
  if not flag:
    print(answer)
    break
  