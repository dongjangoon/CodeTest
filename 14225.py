n = int(input().split()[0])
a = list(map(int, input().split()))
c = [False]*2000001

def go(len, sum):
    if len == n:
        c[sum] = True
        return
    if not len + 1 == n:
        go(len+1, sum + a[len+1])
    go(len+1, sum)

for i in range(n):   
    go(i, a[i])

for i in range(1, 2000001):
    if not c[i]:
        print(i)
        break
