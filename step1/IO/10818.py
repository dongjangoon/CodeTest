n = int(input())
numbers = list(map(int, input().split()))

min = 1000001
max = 0
for i in range(n):
    if min > numbers[i]:
        min = numbers[i]
    if max < numbers[i]:
        max = numbers[i]

print("%s %s" % (min, max))
