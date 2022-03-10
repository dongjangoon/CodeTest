n = int(input())
m = int(input())

vertex = [i for i in range(n + 1)]
graph = []
answer = 0

for _ in range(m):
  a, b, w = map(int, input().split())
  graph.append([a, b, w])

def find(x):
  if x != vertex[x]:
    vertex[x] = find(vertex[x])
  return vertex[x]

graph.sort(key = lambda graph: graph[2])

for a, b, w in graph:
  a_root = find(a)
  b_root = find(b)
  if a_root != b_root:
    if a_root > b_root:
      vertex[a_root] = b_root
    else:
      vertex[b_root] = a_root
    answer += w

print(answer)