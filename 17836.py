from collections import deque

n, m, t = map(int, input().split())
grid = [list(map(int, input().split())) for _ in range(n)]

dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]

queue = deque([(0, 0, 0, 0)])  # 위치, 그람이 있는지 여부, 이동 수
visited = set([(0, 0, 0)])

while queue:
  x, y, gram, moves = queue.popleft()

  if (x, y) == (n-1, m-1):
    if moves <= t:
      print(moves)
      exit()

  for k in range(4):
    nx, ny = x + dx[k], y + dy[k]
    
    if 0 <= nx < n and 0 <= ny < m and (nx, ny, gram) not in visited:
      # 벽이면서 그람이 없는 경우
      if grid[nx][ny] == 1 and gram == 0:
        continue
      
      # 그람인 경우
      elif grid[nx][ny] == 2:
        queue.append((nx, ny, 1, moves+1))
        visited.add((nx, ny, 1))
      
      # 빈 칸 혹은 그람을 가진 채로 벽을 만난 경우
      else:
        queue.append((nx, ny, gram, moves+1))
        visited.add((nx, ny, gram))

print("Fail")
