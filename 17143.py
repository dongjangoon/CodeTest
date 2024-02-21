def get_next_loc(i, j, speed, _dir):
  if _dir == UP or _dir == DOWN: # UP or DOWN
    cycle = 2 * R - 2
    if _dir == UP:
      speed += 2 * (R  - 1) - i
    else:
      speed += i

    speed %= cycle

    if speed >= R:
      return (2 * R - 2 - speed, j, UP)
    return (speed, j, DOWN)
    
  else: # RIGHT OR LEFT
    cycle = 2 * C - 2
    if _dir == LEFT:
      speed += 2 * (C - 1) - j
    else: 
      speed += j
    
    speed %= cycle

    if speed >= C:
      return (i, 2 * C - 2 - speed, LEFT)
    return (i, speed, RIGHT)


def fish(j):
  for i in range(R):
    if graph[i][j]:
      target = graph[i][j][2]
      graph[i][j] = 0
      return target
  return 0

def move():
  global graph
  new_graph = [[0] * C for _ in range(R)]
  for i in range(R):
    for j in range(C):
      if graph[i][j]:
        shark_info = graph[i][j]
        speed, _dir, size = shark_info[0], shark_info[1], shark_info[2]

        ni, nj, nd = get_next_loc(i, j, speed, _dir)
        if new_graph[ni][nj]:
          new_graph[ni][nj] = max(
            new_graph[ni][nj],
            (speed, nd, size),
            key=lambda x: x[2]
          )
        else:
          new_graph[ni][nj] = (speed, nd, size)

  graph = new_graph

UP, DOWN, RIGHT, LEFT = 1, 2, 3, 4
R, C, M = map(int, input().split())
graph = [[0] * C for _ in range(R)]

for _ in range(M):
  r, c, s, d, z = map(int, input().split())
  graph[r-1][c-1] = (s, d, z)

# 열의 수만큼 시간이 지남
# 매 시간마다 속력, 크기 
ans = 0
for j in range(C):
  ans += fish(j)
  move()

print(ans)