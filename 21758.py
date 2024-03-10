n = int(input())
honeys = list(map(int, input().split()))

prefix_sum = []
prefix_sum.append(honeys[0])

for i in range(1, n):
  prefix_sum.append(prefix_sum[i-1] + honeys[i])

# 1. 벌통이 오른쪽 끝에 있을 때, 1번째 벌은 고정
_sum = 0
first_bee = prefix_sum[n-1] - honeys[0]
second_bee = 0

for i in range(1, n-1):
  second_bee = prefix_sum[n-1] - prefix_sum[i] - honeys[i]
  _sum = max(_sum, first_bee + second_bee)

# 2. 벌통이 왼쪽 끝에 있을 때
first_bee = prefix_sum[n-2]

for i in range(1, n-1):
  second_bee = prefix_sum[i-1] - honeys[i]
  _sum = max(_sum, first_bee + second_bee)

# 3. 벌통이 가운데, 벌이 양쪽 끝에
for i in range(1, n-1):
  _sum = max(_sum, prefix_sum[n-2] - honeys[0] + honeys[i])

print(_sum)
