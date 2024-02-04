import sys
sys.setrecursionlimit(10**6)

n = int(input())
arr = [list(map(int, input().split())) for _ in range(n)]
visited = [[0]*n for _ in range(n)]
dx, dy = [1, -1, 0, 0], [0, 0, -1, 1]

def dfs(now):
  y, x = now
  if visited[y][x]:
    return visited[y][x]
  visited[y][x] = 1
  for k in range(4):
    nx, ny = x + dx[k], y + dy[k]
    if 0 <= nx < n and 0 <= ny < n and arr[ny][nx] > arr[y][x]:
      visited[y][x] = max(visited[y][x], dfs((ny, nx)) + 1)

  return visited[y][x]

ans = 0
for i in range(n):
  for j in range(n):
    ans = max(ans, dfs((i, j)))

print(ans)
