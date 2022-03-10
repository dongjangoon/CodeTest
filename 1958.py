first = input()
second = input()
third = input()

dp = [[[0 for i in range(len(first) + 1)] for j in range(len(second) + 1)] for k in range(len(third) + 1)]

for k in range(1, len(third) + 1):
  for j in range(1, len(second) + 1):
    for i in range(1, len(first) + 1):
      if first[i - 1] == second[j - 1] == third[k - 1]:
        dp[k][j][i] = dp[k-1][j-1][i-1] + 1
      else:
        dp[k][j][i] = max(dp[k-1][j][i], dp[k][j-1][i], dp[k][j][i-1], dp[k-1][j-1][i], dp[k-1][j][i-1], dp[k][j-1][i-1])

print(dp[-1][-1][-1])
