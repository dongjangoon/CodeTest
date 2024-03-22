n = int(input())
sand = [list(map(int, input().split())) for _ in range(n)]

# 모래 비율
spread = [(-1, 1, 0.01), (1, 1, 0.01), (-1, 0, 0.07), (1, 0, 0.07), (2, 0, 0.02), (-2, 0, 0.02), (1, -1, 0.1), (-1, -1, 0.1), (0, -2, 0.05)]
dx = [0, 1, 0, -1]
dy = [-1, 0, 1, 0]

# 토네이도 이동
x, y = n//2, n//2
direction = 0
out_sand = 0

def spread_sand(x, y, direction):
  global out_sand
  total_spread = 0
  for dx, dy, ratio in spread:
    nx, ny = x + dx*directions[direction][0] + dy*directions[direction][1], y + dx*directions[direction][0] + dy*directions[direction][1]
    spread_sand = int(sand[y][x] * ratio)
    if 0 <= nx < n and 0 <= ny < n:
      sand[ny][nx] += spread_sand
    else:
      out_sand += spread_sand
    total_spread += spread_sand
  alpha = sand[y][x] - total_spread
  ax, ay = x + directions[direction][0], y + directions[direction][1]
  if 0 <= ax < n and 0 <= ay < n:
    sand[ay][ax] += alpha
  else:
    out_sand += alpha

directions = [(-1, 0), (0, 1), (1, 0), (0, -1)]
for i in range(1, n):
  for _ in range(2):
    for _ in range(i):
      spread_sand(x, y, direction)
      x, y = x + directions[direction][0], y + directions[direction][1]
    direction = (direction + 1) % 4

# 마지막 n만큼 n-1번 이동
for _ in range(n-1):
  spread_sand(x, y, direction)
  x, y = x + directions[direction][0], y + directions[direction][1]

print(out_sand)