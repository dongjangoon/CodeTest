from collections import deque

n, h, d = map(int, input().split())
grid = [input() for _ in range(n)]
dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]

for x in range(n):
  for y in range(n):
    if grid[x][y] == 'S':
      current = (x, y)
    if grid[x][y] == 'E':
      safe = (x, y)

queue = deque([(current[0], current[1], h, 0, 0)])  # s의 위치, 체력, 0 (우산 내구도), 이동 횟수
visited = [[0] * n for _ in range(n)]
visited[current[0]][current[1]] = h

while queue:
  x, y, health, durability, moves = queue.popleft()

  for k in range(4):
    nx, ny = x + dx[k], y + dy[k]

    if 0 <= nx < n and 0 <= ny < n:
      if grid[nx][ny] == 'E':
        print(moves+1)
        exit(0)

      next_h = health
      next_d = durability

      # 우산을 만난 경우
      if grid[nx][ny] == 'U':
        next_d = d

      if next_d == 0:
        next_h -= 1
      else:
        next_d -= 1
      
      if next_h == 0:
        continue

      if visited[nx][ny] < next_h:
        visited[nx][ny] = next_h
        queue.append((nx, ny, next_h, next_d, moves+1))
      
print(-1)
      