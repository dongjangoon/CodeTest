from collections import deque

def bfs(i, j):
  q = deque()
  q.append((i, j))
  visited[i][j] = 1
  cnt = 1

  while q: 
    x, y = q.popleft()
    # group 번호 부여
    groups[x][y] = group_num

    for k in range(4):
      nx, ny = x + dx[k], y + dy[k]

      if 0 <= nx < n and 0 <= ny < m and not graph[nx][ny] and not visited[nx][ny]:
        q.append((nx, ny))
        visited[nx][ny] = 1
        cnt += 1

  return cnt

def union_group(x, y):
  # 그룹 번호 담는 set
  group_num_set = set()

  for k in range(4):
    nx, ny = x + dx[k], y + dy[k]
    if 0 <= nx < n and 0 <= ny < m and groups[nx][ny]:
      # 그룹 번호 추가
      group_num_set.add(groups[nx][ny])

  # 현재 위치 기본 1
  cnt = 1

  for num in group_num_set:
    # 그룹 번호에 해당하는 좌표 개수 카운트
    cnt += group_cnt[num]
    cnt %= 10
  
  return cnt

dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]

n, m = map(int, input().split())
graph = [list(map(int, input())) for _ in range(n)]

visited = [[0] * m for _ in range(n)]
groups = [[0] * m for _ in range(n)]
answer = [[0] * m for _ in range(n)]

group_num = 1
group_cnt = {}

# 먼저 이동할 수 있는 곳 그룹화
for i in range(n):
  for j in range(m):
    # 이동할 수 있거나 아직 방문하지 않았다면
    if not graph[i][j] and not visited[i][j]:
      # 현재 이동할 수 있는 0의 개수 그룹마다 할당
      cnt = bfs(i, j)
      group_cnt[group_num] = cnt
      group_num += 1

# 벽 부수고 인접한 그룹 개수 더하기
for i in range(n):
  for j in range(m):
    if graph[i][j]:
      answer[i][j] = union_group(i, j)

for i in range(n):
  print("".join(map(str, answer[i])))