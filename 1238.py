import heapq

INF = int(1e9)

v, e, x = map(int, input().split())
tree = [[] for _ in range(v+1)]

for _ in range(e):
  a, b, t = map(int, input().split())
  tree[a].append((b, t))

def dijkstra(start):
  q = []
  distance = [INF] * (v + 1)

  heapq.heappush(q, (0, start))
  distance[start] = 0

  while q:
    dist, now = heapq.heappop(q)

    if distance[now] < dist:
      continue

    for cur_v, cur_cost in tree[now]:
      cost = dist + cur_cost

      if distance[cur_v] > cost:
        distance[cur_v] = cost
        heapq.heappush(q, (cost, cur_v))
  
  return distance

result = 0
for i in range(1, v+1):
  go = dijkstra(i)
  back = dijkstra(x)
  result = max(result, go[x] + back[i])
print(result)
