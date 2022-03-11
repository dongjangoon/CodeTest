from collections import deque
t = int(input())

for _ in range(t):
  n, k = map(int, input().split())
  time = [0] + list(map(int, input().split()))
  graph = [[] for _ in range(n + 1)]
  in_degree = [0 for _ in range(n + 1)]
  dp = [0 for _ in range(n + 1)]
  for _ in range(k):
    x, y = map(int, input().split())
    graph[x].append(y)
    in_degree[y] += 1

  w = int(input())

  q = deque()
  for i in range(1, n + 1):
    if in_degree[i] == 0:
      q.append(i)
      dp[i] = time[i]
  
  while q:
    x = q.popleft()
    for i in graph[x]:
      in_degree[i] -= 1
      dp[i] = max(dp[x] + time[i], dp[i])
      if in_degree[i] == 0:
        q.append(i)
  
  print(dp[w])
  