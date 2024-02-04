from collections import deque

n, m = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(n)]
dx, dy = [-1, 1, 0, 0], [0, 0, 1, -1]
answer = 0
flag = False

def bfs(y, x):
  q.append((y, x))
  while q:
    y, x = q.popleft()
    visited[y][x] = 1
    for k in range(4):
      nx, ny = x + dx[k], y + dy[k]
      if 0 <= nx < m and 0 <= ny < n and visited[ny][nx] == 0 and arr[ny][nx] != 0:
        q.append((ny, nx))
        visited[ny][nx] = 1
      elif arr[ny][nx] == 0:
        cnt[y][x] += 1

  return 0

while True:
  cnt = [[0] * m for _ in range(n)]
  visited = [[0] * m for _ in range(n)]
  q = deque()
  target = []

  for i in range(n):
    for j in range(m):
      if arr[i][j] != 0 and visited[i][j] == 0:
        target.append(bfs(i, j))
     
  for i in range(n):
    for j in range(m):
      arr[i][j] -= cnt[i][j]
      if arr[i][j] < 0:
        arr[i][j] = 0

  if len(target) >= 2:
    flag = True
    break
  if len(target) == 0:
    break

  answer += 1
  

if flag:
  print(answer)
else:
  print(0)
  