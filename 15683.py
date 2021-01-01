n, m = map(int, input().split())
a = [list(map(int, input().split())) for _ in range(n)]
dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]

def check(a, b, x, y, dir):
    nx = x
    ny = y
    while 0 <= nx < n and 0 <= ny < m:
        if a[nx][ny] == 6:
            break
        b[nx][ny] = a[x][y]
        nx += dx[dir]
        ny += dy[dir]

def go(a, cctv, index, dirs):
    if len(cctv) == index:
        b = [[0]*m for _ in range(n)]
        for x in range(n):
            for y in range(m):
                b[x][y] = a[x][y]
        
        for i, (what, x, y) in enumerate(cctv):
            if what == 1:
                check(a, b, x, y, dirs[i])
            elif what == 2:
                check(a, b, x, y, dirs[i])
                check(a, b, x, y, (dirs[i]+2) % 4)
            elif what == 3:
                check(a, b, x, y, dirs[i])
                check(a, b, x, y, (dirs[i]+1)%4)
            elif what == 4:
                check(a, b, x, y, dirs[i])
                check(a, b, x, y, (dirs[i]+1)%4)
                check(a, b, x, y, (dirs[i]+2)%4)
            elif what == 5:
                check(a, b, x, y, dirs[i])
                check(a, b, x, y, (dirs[i]+1)%4)
                check(a, b, x, y, (dirs[i]+2)%4)
                check(a, b, x, y, (dirs[i]+3)%4)
        
        cnt = 0
        for i in range(n):
            for j in range(m):
                if b[i][j] == 0:
                    cnt += 1
        return cnt

    ans = 100
    for i in range(4):
        temp = go(a, cctv, index+1, dirs+[i])
        if ans > temp:
            ans = temp
    return ans

cctv = []
for x in range(n):
    for y in range(m):
        if 1 <= a[x][y] <= 5:
            cctv.append((a[x][y], x, y))
print(go(a, cctv, 0, []))