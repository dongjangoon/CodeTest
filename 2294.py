n, k = map(int, input().split())
values = [int(input()) for _ in range(n)]

dp = [10001] * (k + 1)
dp[0] = 0

for i in values:
  for j in range(i, k + 1):
    dp[j] = min(dp[j], dp[j - i] + 1)
if dp[k] == 10001:
  print(-1)
else:
  print(dp[k])

