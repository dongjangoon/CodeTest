from collections import deque

n, m = map(int, input().split())
answer = []
graph = [[] for _ in range(n + 1)]
in_degree = [0 for _ in range(n + 1)]

for _ in range(m):
  x, y = map(int, input().split())
  graph[x].append(y)
  in_degree[y] += 1

q = deque()
for i in range(1, n + 1):
  if in_degree[i] == 0:
    q.append(i)
    answer.append(i)

while q:
  x = q.popleft()
  for i in graph[x]:
    in_degree[i] -= 1
    if in_degree[i] == 0:
      q.append(i)
      answer.append(i)

print(*answer)
