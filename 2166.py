n = int(input())
point = []

for _ in range(n):
  x, y = map(int, input().split())
  point.append((x, y))
point.append(point[0])

xSum, ySum = 0, 0
for i in range(n):
  first = point[i]
  second = point[i+1]
  xSum += first[0] * second[1]
  ySum += second[0] * first[1]

print(round(abs((xSum - ySum)/2), 1))
