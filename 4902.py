def calc(row, left, right, sum):
    if row < 1 or row > n:
        return
    if left < 1 or right > 2*row-1:
        return
    sum += s[row][right] - s[row][left-1]
    global ans
    if sum > ans:
        ans = sum
    if left % 2 == 0:
        calc(row - 1, left - 2, right, sum)
    else:
        calc(row + 1, left, right + 2, sum)


cnt = 0
while True:
    cnt += 1
    inputs = list(map(int, input().split()))
    n = inputs[0]
    if n == 0:
        break
    ans = -100000
    a = [[]] # 좌표마다 삼각형의 값
    s = [[]] # 행마다 이전 열 누적
    k = 1
    for i in range(1, n+1):
        a.append([0]*(2*i))
        s.append([0]*(2*i))
        for j in range(1, 2*i):
            a[i][j] = inputs[k]
            k += 1
            s[i][j] = s[i][j-1] + a[i][j]
    for i in range(1, n+1):
        for j in range(1, 2*i):
            calc(i, j, j, 0)
    print(str(cnt) + '. ' + str(ans))
