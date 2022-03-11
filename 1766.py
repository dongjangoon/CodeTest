import heapq

n, m = map(int, input().split())

graph = [[] for _ in range(n + 1)]
in_degree = [0 for _ in range(n + 1)]
answer = []

for _ in range(m):
  x, y = map(int, input().split())
  graph[x].append(y)
  in_degree[y] += 1

heap = []
for i in range(1, n + 1):
  if in_degree[i] == 0:
    heapq.heappush(heap, i)

while heap:
  x = heapq.heappop(heap)
  answer.append(x)
  for i in graph[x]:
    in_degree[i] -= 1
    if in_degree[i] == 0:
      heapq.heappush(heap, i)

print(*answer)
