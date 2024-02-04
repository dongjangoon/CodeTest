import heapq

n, k = map(int, input().split())

jem = [list(map(int, input().split())) for _ in range(n)]
bag = [int(input()) for _ in range(k)]

jem.sort()
bag.sort()

answer = 0
greedy = []

for i in bag:
  while jem and i >= jem[0][0]:
    heapq.heappush(greedy, -jem[0][1])
    heapq.heappop(jem)
  
  if greedy:
    answer += heapq.heappop(greedy)
  elif not jem:
    break

print(-answer)
