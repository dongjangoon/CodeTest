n = int(input())
d = [[0 for _ in range(10)] for _ in range(101)]

for i in range(1, 10):
    d[1][i] = 1
for i in range(2, 101):
    for j in range(10):
        if j == 0:
            d[i][j] = d[i-1][j+1]
        elif j == 9:
            d[i][j] = d[i-1][j-1]
        else:
            d[i][j] = d[i-1][j-1] + d[i-1][j+1]

        
print(sum(d[n]) % 1000000000)