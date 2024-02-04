n = int(input())
a, b = map(int, input().split())
m = int(input())
graph = [[] for _ in range(n+1)]
visited = [0] * (n+1)

for i in range(m):
  x, y = map(int, input().split())
  graph[x].append(y)
  graph[y].append(x)

def dfs(before):
  for next in graph[before]:
    if next != before and visited[next] == 0:
      visited[next] = visited[before] + 1
      dfs(next)

dfs(a)

if visited[b] == 0:
  print(-1)
else:
  print(visited[b])

