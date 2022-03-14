n, m = map(int, input().split())
graph = []

for _ in range(m):
  x, y, w = map(int, input().split())
  graph.append([w, x, y])

graph.sort()

parent = [0] * (n + 1)
for i in range(1, n + 1):
  parent[i] = i

def find(x):
  if parent[x] != x:
    parent[x] = find(parent[x])
  return parent[x]

def union(x, y):
  x = find(x)
  y = find(y)
  if x < y:
    parent[y] = x
  else:
    parent[x] = y

answer = 0
selected = []

for w, x, y in graph:
  if find(x) != find(y):
    union(x, y)
    answer += w
    selected.append(w)

answer -= selected.pop()
print(answer)
