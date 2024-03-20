from collections import deque

n, m = map(int, input().split())
moves = dict([tuple(map(int, input().split())) for _ in range(n+m)])

queue = deque([(1, 0)])  # 좌표, 이동 횟수
visited = {1: 0}

while queue:
  x, move_count = queue.popleft()

  if x == 100:
    print(move_count)
    break

  for k in range(1, 7):
    nx = x + k
    if nx > 100: continue 
    while nx in moves:
      nx = moves[nx]
    if nx not in visited:
      visited[nx] = move_count + 1
      queue.append((nx, move_count + 1))
