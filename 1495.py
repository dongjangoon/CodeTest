n, s, m = map(int, input().split())
a = list(map(int, input().split()))
d = [[0]*1001 for _ in range(1001)]

d[0][s] = 1

for i in range(n):
    for j in range(m+1):
        if d[i][j] == 1:
            if j - a[i] >= 0:
                d[i+1][j - a[i]] = 1
            if j + a[i] <= m:
                d[i+1][j + a[i]] = 1

ans = -1
for i in range(1000, -1, -1):
    if d[n][i] == 1:
        ans = i
        break
print(ans)