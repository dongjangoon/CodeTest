n, m = map(int, input().split())
a = [list(input()) for _ in range(n)]
dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]
x1=y1=x2=y2=-1

def go(count, x1, y1, x2, y2):
    if count == 11:
        return -1
    fall1 = fall2 = False
    if 0 > x1 or x1 >= n or 0 > y1 or y1 >= m:
        fall1 = True
    if 0 > x2 or x2 >= n or 0 > y2 or y2 >= m:
        fall2 = True

    if fall1 and fall2:
        return -1
    if fall2 or fall1:
        return count
    
    ans = -1
    for k in range(4):
        nx1 = x1 + dx[k]
        ny1 = y1 + dy[k]
        nx2 = x2 + dx[k]
        ny2 = y2 + dy[k]
        if 0 <= nx1 < n and 0 <= ny1 < m and a[nx1][ny1] == '#':
            nx1, ny1 = x1, y1
        if 0 <= nx2 < n and 0 <= ny2 < m and a[nx2][ny2] == '#':
            nx2, ny2 = x2, y2
        temp = go(count+1, nx1, ny1, nx2, ny2)
        if temp == -1:
            continue
        if ans == -1 or ans > temp:
            ans = temp
    return ans

for i in range(n):
    for j in range(m):
        if a[i][j] == 'o':
            if x1 == -1:
                x1, y1 = i, j
            else:
                x2, y2 = i, j
            a[i][j] = '.'

print(go(0, x1, y1, x2, y2))