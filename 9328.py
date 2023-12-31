from collections import deque

dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]

def bfs(x, y):
  ans = 0
  deq = deque()
  visited = [[0] * (w+2) for _ in range(h+2)]
  deq.append([x, y])
  visited[x][y] = 1

  while deq:
    x, y = deq.popleft()
    for i in range(4):
      nx, ny = x + dx[i], y + dy[i]

      if 0 <= nx < h+2 and 0 <= ny < w+2:
        if not visited[nx][ny]:
          if graph[nx][ny] == '.':
            visited[nx][ny] = 1
            deq.append([nx, ny])
          elif graph[nx][ny].islower():
            door[ord(graph[nx][ny]) - ord('a')] = 1
            deq = deque()
            visited = [[0] * (w+2) for _ in range(h+2)]
            graph[nx][ny] = '.'
            deq.append([nx, ny])
          elif graph[nx][ny].isupper():
            if door[ord(graph[nx][ny]) - ord('A')]:
              graph[nx][ny] = '.'
              visited[nx][ny] = 1
              deq.append([nx, ny])
          elif graph[nx][ny] == '$':
            ans += 1
            visited[nx][ny] = 1
            graph[nx][ny] = '.'
            deq.append([nx, ny])
  print(ans)

t = int(input())
for _ in range(t):
  h, w = map(int, input().split())
  a = ['.' + input() + '.' for _ in range(h)]
  a = ['.' * (w+2)] + a + ['.' * (w+2)]
  keys = list(input().strip())
  door = [0] * 26

  for key in keys:
    if key != '0':
      door[ord(key) - ord('a')] = 1

  graph = []
  for i in range(h+2):
    graph.append(list(a[i]))

  for i in range(h+2):
    for j in range(w+2):
      if ord('A') <= ord(graph[i][j]) <= ord('Z'):
        if door[ord(graph[i][j]) - ord('A')]:
          graph[i][j] = '.'

  bfs(0, 0)
