n, m = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(n)]
check = [[0] * m for _ in range(n)]
dx, dy = [-1, 1, 0, 0], [0, 0, 1, -1]
answer = 0

def dfs(y, x, idx, total):
  global answer
  if idx == 3:
    answer = max(answer, total)
  else:
    for k in range(4):
      ny, nx = y + dy[k], x + dx[k]
      if 0 <= ny < n and 0 <= nx < m and check[ny][nx] == 0:
        check[ny][nx] = 1
        dfs(ny, nx, idx + 1, total + arr[ny][nx])
        check[ny][nx] = 0

def block(y, x, total):  # ㅗ 모양만 따로 분리해서 작성
  global answer
  block_num = 0
  for k in range(4):
    ny, nx = y + dy[k], x + dx[k]
    if 0 <= ny < n and 0 <= nx < m:
      block_num += 1
      total += arr[ny][nx]
  
  if block_num == 3:
    answer = max(answer, total)
  
  if block_num == 4:
    for k in range(4):
      ny, nx = y + dy[k], x + dx[k]
      total -= arr[ny][nx]
      answer = max(answer, total)
      total += arr[ny][nx]

for i in range(n):
  for j in range(m):
    check[i][j] = 1
    dfs(i, j, 0, arr[i][j])
    block(i, j, arr[i][j])
    check[i][j] = 0

print(answer)
