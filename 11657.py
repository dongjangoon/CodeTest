v, e = map(int, input().split())
INF = int(1e9)
distances = [INF] * (v+1)
edges = []

for _ in range(e):
  a, b, c = map(int, input().split())
  edges.append((a, b, c))

def bellman_ford(start):
  distances[start] = 0
  # 전체 v -1 번의 라운드를 반복 
  for i in range(v):
    # 매 반복마다 모든 간선을 확인
    for j in range(e):
      cur = edges[j][0]
      next = edges[j][1]
      cost = edges[j][2]

      if distances[cur] != INF and distances[cur] + cost < distances[next]:
        distances[next] = distances[cur] + cost
        # 마지막 라운드에서도 값이 갱신된다면 음수 순환이 존재
        if i == v - 1:
          return True
  return False

negative_cycle = bellman_ford(1)

if negative_cycle:
  print(-1)
else:
  for i in range(2, v+1):
    if distances[i] == INF:
      print(-1)
    else:
      print(distances[i])
      