n = int(input())
arr = [list(map(int, input().split())) for _ in range(n)]
dp = [[0]*n for _ in range(n)]

for cnt in range(1, n):
  for start in range(n):
    end = start + cnt
    if end == n:
      break

    dp[start][end] = int(1e9)

    for mid in range(start, end):
      dp[start][end] = min(dp[start][end], 
                           dp[start][mid] + dp[mid+1][end] +
                           arr[start][0] * arr[mid][1] * arr[end][1])
      
print(dp[0][n-1])