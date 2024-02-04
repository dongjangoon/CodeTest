from collections import deque

r, c = map(int, input().split())
arr = [list(map(str, input().strip())) for _ in range(r)]

dx, dy = [-1, 0, 1, 0], [0, 1, 0, -1]
bx, by = 0, 0
hx, hy = 0, 0
wx, wy = 0, 0

w = deque()
q = deque()
visited = [[0] * c for _ in range(r)]

for i in range(r):
  for j in range(c):
    if arr[i][j] == "D":
      bx, by = i, j
    if arr[i][j] == 'S':
      arr[i][j] = 0
      visited[i][j] = 1
      q.append((i, j))
    if arr[i][j] == "*":
      w.append((i, j))

while w or q:
  temp_q, temp_w = [], []
  while q:
    x, y = q.popleft()
    if arr[x][y] != '*':
      for k in range(4):
        nx, ny = x + dx[k], y + dy[k]
        if nx < 0 or ny < 0 or nx >= r or ny >= c:
          continue
        if visited[nx][ny] == 0 and arr[nx][ny] != 'X' and arr[nx][ny] != '*':
          visited[nx][ny] = 1
          arr[nx][ny] = arr[x][y] + 1
          temp_q.append((nx, ny))

  while w:
    x, y = w.popleft()
    for k in range(4):
      nx, ny = x + dx[k], y + dy[k]
      if nx == bx and ny == by:
        continue
      if 0 <= nx < r and 0 <= ny < c and arr[nx][ny] != '*' and arr[nx][ny] != 'X':
        arr[nx][ny] = '*'
        temp_w.append((nx, ny))

  for i in temp_q:
    q.append(i)
  for i in temp_w:
    w.append(i)

result = arr[bx][by]
print(result if result != 'D' else "KAKTUS")