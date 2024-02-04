n = int(input())

for i in range(1, 2*n):
    if i > n:
        print(" "*(2*n-1-i)+"*"*(2*(i-n)+1))
    else:
        print(" "*(i-1)+"*"*(2*(n-i)+1))