n = int(input())

for i in range(1, 2*n):
    if i > n:
        print("*"*(2*n-i)+" "*(2*(i-n)) + "*"*(2*n-i))
    else:
        print("*"*i + " "*(2*(n-i)) + "*"*i)