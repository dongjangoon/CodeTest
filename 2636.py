from collections import deque

n, m = map(int, input().split())
grid = [list(map(int, input().split())) for _ in range(n)]

dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]


def bfs():
  queue = deque([(0, 0)])
  visited = [[False] * m for _ in range(n)]
  visited[0][0] = True
  melting_list = []

  while queue:
    x, y = queue.popleft()

    for k in range(4):
      nx, ny = x + dx[k], y + dy[k]

      if 0 <= nx < n and 0 <= ny < m and not visited[nx][ny]:
        visited[nx][ny] = True
        
        if grid[nx][ny] == 1:
          melting_list.append((nx, ny))
        elif grid[nx][ny] == 0:
          queue.append((nx, ny))

  count = 0
  for x, y in melting_list:
    count += 1
    grid[x][y] = 0
  return count


total_time = 0
last_cheese = 0

while True:
  melted = bfs()
  if melted == 0:
    break
  last_cheese = melted
  total_time += 1

print(total_time)
print(last_cheese)
