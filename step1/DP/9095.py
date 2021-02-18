t = int(input())
d = [0]*12


def dp(n):
    if n == 1:
        return 1
    if n == 2:
        return 2
    if n == 3:
        return 4

    if d[n]:
        return d[n]

    d[n] = dp(n-1) + dp(n-2) + dp(n-3)
    return d[n]


for i in range(t):
    n = int(input())
    print(dp(n))
