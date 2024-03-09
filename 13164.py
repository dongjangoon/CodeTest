n, k = map(int, input().split())
heights = list(map(int, input().split()))

# 인접한 원생들 사이의 키 차이를 계산
differences = []
for i in range(1, n):
  differences.append(heights[i] - heights[i-1])

# 키 차이를 오름차순으로 정렬
differences.sort()

# k-1개의 가장 큰 키 차이를 제외한 나머지의 합이 최소 비용
min_cost = sum(differences[:n-k])

print(min_cost)
