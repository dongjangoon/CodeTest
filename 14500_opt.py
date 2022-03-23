n, m = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(n)]
check = [[0] * m for _ in range(n)]
dx, dy = [-1, 1, 0, 0], [0, 0, 1, -1]
answer = 0
max_val = max(map(max, arr))

def dfs(y, x, idx, total):
  global answer
  if answer >= total + max_val * (3 - idx):  # 가지치기, 이미 최대값이 나왔다면 그냥 리턴
    return
  if idx == 3:
    answer = max(answer, total)
    return
  else:
    for k in range(4):
      ny, nx = y + dy[k], x + dx[k]
      if 0 <= ny < n and 0 <= nx < m and check[ny][nx] == 0:
        if idx == 1:  # 두 번째 블록이 선택되었을 때, ㅗ 모양에 대해 백트래킹 진행
          check[ny][nx] = 1
          dfs(y, x, idx + 1, total + arr[ny][nx])
          check[ny][nx] = 0
        check[ny][nx] = 1
        dfs(ny, nx, idx + 1, total + arr[ny][nx])
        check[ny][nx] = 0

for i in range(n):
  for j in range(m):
    check[i][j] = 1
    dfs(i, j, 0, arr[i][j])
    check[i][j] = 0

print(answer)
