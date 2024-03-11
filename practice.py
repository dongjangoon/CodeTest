n, k = map(int, input().split())
heights = list(map(int, input().split()))
diff = []

for i in range(1, n):
  diff.append(heights[i] - heights[i-1])

diff.sort()

print(sum(diff[:n-k]))