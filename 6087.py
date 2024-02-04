import heapq

def dijkstra():
  dp = [[INF]*(h+1) for _ in range(w+1)]
  dp[start[0]][start[1]] = 0
  heap = []
  heapq.heappush(heap, (0, start[0], start[1], 0))
  heapq.heappush(heap, (0, start[0], start[1], 1))
  heapq.heappush(heap, (0, start[0], start[1], 2))
  heapq.heappush(heap, (0, start[0], start[1], 3))

  board[start[0]][start[1]] = '*'

  while heap:
    result, x, y, direction = heapq.heappop(heap)
    if board[x][y] == 'C':
      return result
    if dp[x][y] < result:
      continue

    for i in range(4):
      nx = x + dx[i]
      ny = y + dy[i]
      if 0 <= nx < w and 0 <= ny < h and board[nx][ny] != '*':
        if direction == i and result <= dp[nx][ny]:
          dp[nx][ny] = result
          heapq.heappush(heap, (result, nx, ny, direction))
        elif direction != i and result + 1 <= dp[nx][ny]:
          dp[nx][ny] = result + 1
          heapq.heappush(heap, (result + 1, nx, ny, i))

INF = int(1e9)
h, w = map(int, input().split())
board = [list(input().rstrip()) for _ in range(w)]
lazer = []

dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]

for i in range(w):
  for j in range(h):
    if board[i][j] == 'C':
      lazer.append((i, j))

start, end = lazer[0], lazer[1]
print(dijkstra())