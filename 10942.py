n = int(input())
numbers = list(map(int, input().split()))
dp = [[0] * n for _ in range(n)]

for i in range(n):
  for start in range(n-i):
    end = start + i
    if start == end:
      dp[start][end] = 1
    elif numbers[start] == numbers[end]:
      if start + 1 == end:
        dp[start][end] = 1
      elif dp[start+1][end-1] == 1:
        dp[start][end] = 1

m = int(input())
for _ in range(m):
  s, e = map(int, input().split())
  print(dp[s-1][e-1])
