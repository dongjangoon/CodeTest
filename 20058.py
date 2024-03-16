from collections import deque

n, q = map(int, input().split())
ice = [list(map(int, input().split())) for _ in range(2**n)]
stages = list(map(int, input().split()))

dx = [-1, 1, 0, 0]
dy = [0, 0, 1, -1]


# 부분 격자 회전 함수
def rotate(x, y, l):
  temp = [row[y:y+l] for row in ice[x:x+l]]
  for i in range(l):
    for j in range(l):
      ice[x+j][y+l-1-i] = temp[i][j]


# 얼음 녹이기
def melt():
  melt_list = []
  for x in range(2**n):
    for y in range(2**n):
      if ice[x][y] == 0:
        continue
      count = 0
      for k in range(4):
        nx, ny = x + dx[k], y + dy[k]
        if 0 <= nx < 2**n and 0 <= ny < 2**n and ice[nx][ny] > 0:
          count += 1
      if count < 3:
        melt_list.append((x, y))
  for x, y in melt_list:
    ice[x][y] = max(0, ice[x][y] - 1)


def get_chunk_size(x, y, visited):
  count = 1
  queue = deque([(x, y)])
  visited[x][y] = True
  while queue:
    x, y = queue.popleft()
    for k in range(4):
      nx, ny = x + dx[k], y + dy[k]
      if 0 <= nx < 2**n and 0 <= ny < 2**n and not visited[nx][ny] and ice[nx][ny] > 0:
        queue.append((nx, ny))
        count += 1
        visited[nx][ny] = True
  return count


# 파이어스톰 시전
for l in stages:
  l = 2**l
  for x in range(0, 2**n, l):
    for y in range(0, 2**n, l):
      rotate(x, y, l)
  melt()

# 남아있는 얼음의 합과 가장 큰 덩어리 계산
total_ice = sum(sum(row) for row in ice)
max_chunk = 0
visited = [[False] * (2**n) for _ in range(2**n)]
for x in range(2**n):
  for y in range(2**n):
    if ice[x][y] > 0 and not visited[x][y]:
      max_chunk = max(max_chunk, get_chunk_size(x, y, visited))

print(total_ice)
print(max_chunk)
