n = int(input())
m = int(input())
inf = 1000000

graph = [[inf] * (n + 1) for _ in range(n + 1)]

for _ in range(m):
  a, b, w = map(int, input().split())
  if graph[a][b] > w:
    graph[a][b] = w

for k in range(1, n + 1):
  for i in range(1, n + 1):
    for j in range(1, n + 1):
      if i != j and (graph[i][k] + graph[k][j]) < graph[i][j]:
        graph[i][j] = graph[i][k] + graph[k][j]

for i in range(1, n + 1):
  for j in range(1, n + 1):
    if graph[i][j] == inf:
      print(0, end=' ')
    else:
      print(graph[i][j], end=' ')
  print()
