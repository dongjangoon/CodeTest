from collections import deque

n = int(input())
space = [list(map(int, input().split())) for _ in range(n)]
dx, dy = [0, -1, 0, 1], [1, 0, -1, 0]

shark_size = 2
size_count = 0
answer = 0
sx, sy = 0, 0

for i in range(n):
  for j in range(n):
    if space[i][j] == 9:
      sx, sy = i, j
      space[i][j] = 0
      break

while True:
  q = deque()
  q.append((sx, sy, 0))
  check = [[False] * n for _ in range(n)]
  fish = []
  flag = 2e9
  while q:
    x, y, count = q.popleft()
    if count > flag:
      break

    for k in range(4):
      nx, ny = x + dx[k], y + dy[k]
      if nx < 0 or nx >= n or ny < 0 or ny >= n or space[nx][ny] > shark_size or check[nx][ny]:
        continue
      elif 0 < space[nx][ny] < shark_size:
        fish.append((nx, ny, count + 1))
        flag = count
      check[nx][ny] = True
      q.append((nx, ny, count + 1))

  if len(fish) > 0:
    fish.sort()
    x, y, move = fish[0][0], fish[0][1], fish[0][2]
    answer += move
    size_count += 1
    space[x][y] = 0
    if size_count == shark_size:
      shark_size += 1
      size_count = 0
    sx, sy = x, y
  else:
    break

print(answer)
