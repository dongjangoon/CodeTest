from collections import deque

n, m = map(int, input().split())
grid = [list(map(int, input().split())) for _ in range(n)]
h, w, start_x, start_y, end_x, end_y = map(int, input().split())

dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]

walls = []

for i in range(n):
    for j in range(m):
      if grid[i][j] == 1:
        walls.append((i, j))


def is_valid(x, y):
  minx, maxx = x, x+h
  miny, maxy = y, y+w
  
  for (r, c) in walls:
    if minx <= r < maxx and miny <= c < maxy:
      return False
  
  return True


def bfs():
  queue = deque([(start_x-1, start_y-1, 0)]) 
  visited = [[False] * m for _ in range(n)]
  visited[start_x-1][start_y-1] = True
  
  while queue:
    x, y, moves = queue.popleft()
    
    if (x, y) == (end_x - 1, end_y -1):
      return moves

    for k in range(4):
      nx, ny = x + dx[k], y + dy[k]

      if 0 <= nx < n-h+1 and 0 <= ny < m-w+1 and not visited[nx][ny] and is_valid(nx, ny):
        queue.append((nx, ny, moves+1))
        visited[nx][ny] = True

  return -1


print(bfs())
