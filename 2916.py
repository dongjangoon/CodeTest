n, k = map(int, input().split())
a = list(map(int, input().split()))
b = list(map(int, input().split()))

d = [False] * 360
d[0] = True

for i in range(n):
    for j in range(360):
        for f in range(360):
            if not d[f]:
                continue
            d[(f-a[i]+360)%360] = True
            d[(f+a[i])%360] = True

for i in b:
    print('YES' if d[i] else 'NO')      
