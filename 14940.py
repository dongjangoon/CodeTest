from collections import deque

n, m = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(n)]
dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]
target = (0, 0)

for x in range(n):
  for y in range(m):
    if board[x][y] == 2:
      target = (x, y)
      break

# 방문한 지점 및 거리 저장
visited = [[-1] * m for _ in range(n)]
visited[target[0]][target[1]] = 0

queue = deque([(target[0], target[1])])

while queue:
  x, y = queue.popleft()

  for i in range(4):
    nx, ny = x + dx[i], y + dy[i]

    if 0 <= nx < n and 0 <= ny < m and board[nx][ny] == 1 and visited[nx][ny] == -1:
      visited[nx][ny] = visited[x][y] + 1
      queue.append((nx, ny))

for x in range(n):
  for y in range(m):
    if board[x][y] == 0:
      print(0, end=' ')
    else:
      print(visited[x][y], end=' ')
  print()
