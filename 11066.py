t = int(input())

for _ in range(t):
  k = int(input())
  files = [0] + list(map(int, input().split()))
  dp = [[0] * (k+1) for _ in range(k+1)]

  # 파일이 연속일 때 합
  for i in range(1, k+1):
    for j in range(1, k+1):
      if j == i + 1:
        dp[i][j] = files[i] + files[j]

  for i in range(k-1, 0, -1):
    for j in range(1, k+1):
      if dp[i][j] == 0 and j > i:
        dp[i][j] = min([dp[i][l] + dp[l+1][j] for l in range(i, j)]) + sum(files[i:j+1])

  print(dp[1][k])
