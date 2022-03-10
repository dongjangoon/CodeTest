v, e = map(int, input().split())
vertexes = [i for i in range(v+1)]
graph = []

for _ in range(e):
  a, b, w = map(int, input().split())
  graph.append([a, b, w])

graph.sort(key = lambda graph: graph[2])  # key 기준 오름차순

def find(x):
  if x != vertexes[x]:
    vertexes[x] = find(vertexes[x])
  return vertexes[x]

answer = 0

# Kruskal
for a, b, w in graph:
  a_root = find(a)
  b_root = find(b)
  if a_root != b_root:
    if a_root > b_root:
      vertexes[a_root] = b_root
    else:
      vertexes[b_root] = a_root
    answer += w
print(answer)
