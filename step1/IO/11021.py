t = int(input())
cnt = 1
while t:
    a, b = map(int, input().split())
    print("Case #" + str(cnt) + ": " + str(a+b))
    cnt += 1
    t -=1