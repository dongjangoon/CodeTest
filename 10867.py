n = int(input())
a = list(map(int, input().split()))
numbers = set(a)
ans = list(numbers)

ans2 = sorted(ans)

for i in range(len(ans2)):
    print(ans2[i], end=' ')