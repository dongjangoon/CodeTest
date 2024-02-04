r, c = map(int, input().split())
graph = [list(input().strip()) for _ in range(r)]

dx = [-1, 0, 1]
dy = [1, 1, 1]

visited = [[0] * c for _ in range(r)]
ans = 0

def dfs(x, y):
  if y == c-1:
    return True
  
  for i in range(3):
    nx = x + dx[i]
    ny = y + dy[i]

    if 0 <= nx < r and 0 <= ny < c:
      if graph[nx][ny] != 'x' and visited[nx][ny] == 0:
        visited[nx][ny] = 1
        if dfs(nx, ny):
          return True
      
  return False

for i in range(r):
  if dfs(i, 0):
    ans += 1

print(ans)