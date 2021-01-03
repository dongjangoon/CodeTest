import sys
n, m, h = map(int, input().split())
horizon = [[0]*(n+1) for _ in range(h+1)]
for _ in range(m): # 가로선의 왼쪽은 1로 오른쪽은 2로 표시
    x, y = map(int, input().split())
    horizon[x][y] = 1
    horizon[x][y+1] = 2

a = [] # a에 빈 곳들을 기록 순서대로 사다리를 놓아본다
for i in range(1, h+1):
    for j in range(1, n):
        if horizon[i][j] != 0:
            continue
        if horizon[i][j+1] != 0:
            continue
        a.append((i, j))

def start(c): # 한 열만 검사
    r = 1
    while r <= h:
        if horizon[r][c] == 1:
            c += 1
        elif horizon[r][c] == 2:
            c -= 1
        r += 1
    return c

def go(): # 전체 사다리를 검사
    for i in range(1, n):
        res = start(i)
        if res != i:
            return False
    return True

ans = -1
if go(): # 처음부터 될 경우
    print(0)
    sys.exit(0)

for i in range(len(a)): # 3중 for문
    x1, y1 = a[i]
    if horizon[x1][y1] != 0 or horizon[x1][y1+1] != 0:
        continue
    horizon[x1][y1] = 1
    horizon[x1][y1+1] = 2
    if go():
        if ans == -1 or ans > 1:
            ans = 1
    for j in range(i+1, len(a)):
        x2, y2 = a[j]
        if horizon[x2][y2] != 0 or horizon[x2][y2+1] != 0:
            continue
        horizon[x2][y2] = 1
        horizon[x2][y2+1] = 2
        if go():
            if ans == -1 or ans > 2:
                ans = 2
        for k in range(j+1, len(a)):
            x3, y3 = a[k]
            if horizon[x3][y3] != 0 or horizon[x3][y3+1] != 0:
                continue
            horizon[x3][y3] = 1
            horizon[x3][y3+1] = 2
            if go():
                if ans == -1 or ans > 3:
                    ans = 3
            horizon[x3][y3] = 0
            horizon[x3][y3+1] = 0
        horizon[x2][y2] = 0
        horizon[x2][y2+1] = 0
    horizon[x1][y1] = 0
    horizon[x1][y1+1] = 0
print(ans)