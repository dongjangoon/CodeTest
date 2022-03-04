import sys
sys.setrecursionlimit(10**9)

n = int(input())
graph = [[] for _ in range(n+1)]

def dfs(x, weight):
  for i, w in graph[x]:
    if distance[i] == -1:
      distance[i] = w + weight
      dfs(i, weight + w)

for _ in range(n-1):
  x, y, weight = map(int, input().split())
  graph[x].append([y, weight])
  graph[y].append([x, weight])

distance = [-1]*(n+1)
distance[1] = 0
dfs(1, 0)

start = distance.index(max(distance))
distance = [-1] * (n + 1)
distance[start] = 0
dfs(start, 0)

print(max(distance))
