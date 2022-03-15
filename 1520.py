m, n = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(m)]
dx, dy = [-1, 1, 0, 0], [0, 0, -1, 1]
dp = [[-1] * (n) for _ in range(m)]

def dfs(x, y):
  if x == n-1 and y == m-1:
    return 1
  if dp[y][x] != -1:
    return dp[y][x]
  dp[y][x] = 0
  for k in range(4):
    nx, ny = x + dx[k], y + dy[k]
    if 0 <= nx < n and 0 <= ny < m and arr[ny][nx] < arr[y][x]:
      dp[y][x] += dfs(nx, ny) 
  return dp[y][x]

print(dfs(0, 0))
