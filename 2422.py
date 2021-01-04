n, m = map(int, input().split())
a = [[False]*(n+1) for _ in range(n+1)]

for _ in range(m):
    u, v = map(int, input().split())
    a[u][v] = a[v][u] = True

ans = 0
for i in range(1, n-1):
    for j in range(i+1, n):
        if a[i][j]:
            continue
        for k in range(j+1, n+1):
            if a[j][k]:
                continue
            if a[i][k]:
                continue
            ans += 1
print(ans)

