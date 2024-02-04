from collections import deque
import sys
from tabnanny import check

n, m = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(n)]
temp = [[0] * m for _ in range(n)]
visited = [[False] * m for _ in range(n)]
dx, dy = [-1, 1, 0, 0], [0, 0, 1, -1]
islands = []
bridges = []
number = 0

# numbering
for i in range(n):
  for j in range(m):
    if arr[i][j] == 1 and not visited[i][j]:
      temp[i][j] = number  # 섬이면
      q = deque()
      q.append((i, j))
      islands.append((i, j, number))
      visited[i][j] = True
      while q:
        y, x = q.popleft()
        for k in range(4):
          ny, nx = y + dy[k], x + dx[k]
          if 0 <= ny < n and 0 <= nx < m and not visited[ny][nx]:
            visited[ny][nx] = True
            if arr[ny][nx] == 1:
              q.append((ny, nx))
              islands.append((ny, nx, number))
              temp[ny][nx] = number
      number += 1

# make bridges          
for y, x, w in islands: # 현재 섬의 번호
  q = deque()
  for k in range(4):
    q.append((y, x))
    visited = [[False] * m for _ in range(n)]
    cnt = [[0] * m for _ in range(n)]
    visited[y][x] = True

    while q:
      y, x = q.popleft()
      ny, nx = y + dy[k], x + dx[k]
      if 0 <= ny < n and 0 <= nx < m and not visited[ny][nx]:
        visited[ny][nx] = True
        if temp[ny][nx] == 0:
          cnt[ny][nx] = cnt[y][x] + 1
          q.append((ny, nx))
        elif temp[ny][nx] != w and cnt[y][x] >= 2:
          bridges.append((cnt[y][x], w, temp[ny][nx]))

parents = [i for i in range(number)]

def find(x):
  if parents[x] != x:
    parents[x] = find(parents[x])
  return parents[x]

def union(x, y):
  x = find(x)
  y = find(y)
  parents[y] = x

bridges = sorted(bridges)
answer, cnt = 0, number-1
while cnt:
  try:
    w, x, y = bridges.pop()
  except:
    print(-1)
    sys.exit()
  if find(y) != find(x):
    union(y, x)
    answer += w
    cnt -= 1
print(answer)
