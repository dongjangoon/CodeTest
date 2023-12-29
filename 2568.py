from bisect import bisect_left

n = int(input())
lines = []

for _ in range(n):
  a, b = map(int, input().split())
  lines.append([a, b])

lines.sort(key=lambda x:x[0])

dp = []
check = []

for i, j in lines:
  if not dp:
    dp.append(j)
  if dp[-1] < j:
    dp.append(j)
    check.append((len(dp)-1, j))
  else:
    index = bisect_left(dp, j)
    dp[index] = j
    check.append((index, j))

answer = []

last_index = len(dp)-1

for i in range(len(check)-1, -1, -1):
  if check[i][0] == last_index:
    answer.append(check[i][1])
    last_index -= 1
  
print(n-len(dp))
A_line = []

for i in range(-1, -n-1, -1):
  for j in range(len(answer)):
    if lines[i][1] == answer[j]:
      A_line.append(lines[i][0])

A_line.sort()
count = 0

for i in range(n):
  p = 0
  for j in range(len(A_line)):
    if lines[i][0] == A_line[j]:
      p = 1
  if p == 0:
    print(lines[i][0])