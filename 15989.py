t = int(input())
numbers = []

for i in range(t):
    numbers.append(int(input()))

d = [-1 for _ in range(10001)]

d[0] = 1
d[1] = 1
d[2] = 2
d[3] = 3

for i in range(4, 10001):
    d[i] = d[i-1] + (d[i-2] - d[i-3])
    if i % 3 == 0:
        d[i] += 1

for i in numbers:
    print(d[i])

