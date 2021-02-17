n, m, k = map(int, input().split())
a = [list(map(int, input().split)) for _ in range(n)]
trees = [list(map(int, input().split())) for _ in range(m)]
c = [[0]*n for _ in range(n)]

for x, y, z in trees:
    c[x][y] = z

while k > 0:

    # spring

    