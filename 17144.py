dx, dy = [1, -1, 0, 0], [0, 0, 1, -1]
r, c, t = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(r)]
dust, cleaner = [], []
for i in range(r):
  for j in range(c):
    if arr[i][j] == -1:
      cleaner.append(i)
a, b = cleaner[0], cleaner[1]

for _ in range(t):
  dust = []
  for i in range(r):
    for j in range(c):
      if arr[i][j] > 0:
        dust.append((i, j, arr[i][j]))

  for y, x, amount in dust:
    if amount >= 5:
      count = 0
      for k in range(4):
        nx, ny = x + dx[k], y + dy[k]
        if 0 <= nx < c and 0 <= ny < r and arr[ny][nx] != -1:
          arr[ny][nx] += amount // 5
          count += 1
      arr[y][x] -= (amount // 5) * count

  for i in range(a-1, 0, -1):
    arr[i][0] = arr[i-1][0]
  for i in range(0, c-1):
    arr[0][i] = arr[0][i+1]
  for i in range(0, a):
    arr[i][c-1] = arr[i+1][c-1]
  for i in range(c-1, 1, -1):
    arr[a][i] = arr[a][i-1]
  arr[a][1] = 0

  for i in range(b+1, r-1):
    arr[i][0] = arr[i+1][0]
  for i in range(0, c-1):
    arr[r-1][i] = arr[r-1][i+1]
  for i in range(r-1, b, -1):
    arr[i][c-1] = arr[i-1][c-1]
  for i in range(c-1, 1, -1):
    arr[b][i] = arr[b][i-1]
  arr[b][1] = 0

answer = 0
for i in range(r):
  for j in range(c):
    if arr[i][j] > 0:
      answer += arr[i][j]

print(answer)


