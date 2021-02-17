n = int(input())

for i in range(1, 2*n):
    if i > n:
        print(" "*(i-n)+"*"*(2*n-i))
    else:
        print(" "*(n-i)+"*"*i)