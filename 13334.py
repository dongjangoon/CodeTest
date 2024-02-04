import heapq

n = int(input())
roads, data = [], []

for _ in range(n):
  data.append(sorted(list(map(int, input().split()))))
d = int(input())

for road in data:
  h, o = road
  if (o - h) <= d:
    roads.append(road)

roads.sort(key=lambda x: x[1])

ans = 0
q = []

for road in roads:
  if not q:
    heapq.heappush(q, road)
  else:
    while q[0][0] < road[1] - d:
      heapq.heappop(q)
      if not q:
        break
    
    heapq.heappush(q, road)
  ans = max(ans, len(q))

print(ans)
