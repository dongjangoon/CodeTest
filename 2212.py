n = int(input())
k = int(input())
sensors = list(map(int, input().split()))
sensors.sort()

diff = []
for i in range(1, n):
  diff.append(sensors[i] - sensors[i-1])

diff.sort(reverse=True)

# k-1 개의 가장 긴 거리를 제거하여 k개의 영역으로 나눔
for i in range(k-1):
  if diff:
    diff.pop(0)

min_cost = sum(diff)

print(min_cost)
