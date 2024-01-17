n, m = map(int, input().split())
memories = [0] + list(map(int, input().split()))
costs = [0] + list(map(int, input().split()))
# 첫 번째 앱부터 i번째 앱까지 j 바이트의 메모리를 확보하는데 드는 최소 비용
dp = [[0 for _ in range(sum(costs)+1)] for _ in range(n+1)]
result = sum(costs)

for i in range(1, n+1):
  memory = memories[i]
  cost = costs[i]

  for j in range(0, sum(costs) + 1):
    if j < cost: # 현재 앱을 비활성화할만큼의 cost가 충분하지 않을 경우
      dp[i][j] = dp[i-1][j]
    else:
      # 같은 cost 내에서 현재 앱을 끈 뒤의 byte와 현재 앱을 끄지 않은 뒤의 byte를 비교
      dp[i][j] = max(dp[i-1][j-cost] + memory, dp[i-1][j])

for i, memory in enumerate(dp[n]):
  if memory >= m:
    print(i)
    break
