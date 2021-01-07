from collections import deque

n = int(input())
a = [[] for _ in range(n)]
q = deque()
dx0 = [-1, -1, 0, 0, 1, 1] # 0은 홀수 줄, 1은 짝수 줄
dy0 = [-1, 0, 1, -1, -1, 0] # 왼쪽 위, 오른쪽 위, 오른쪽, 왼쪽, 왼쪽 아래, 오른쪽 아래
dx1 = [-1, -1, 0, 0, 1, 1]
dy1 = [0, 1, -1, 1, 1, 0]
dx = [dx0, dx1]
dy = [dy0, dy1]

dist = [[-1]*n for _ in range(n)]
check = [[False]*n for _ in range(n)]
via = [[-1]*n for _ in range(n)]

check[0][0] = True
dist[0][0] = 1
q.append((0, 0))

def ok(x, y):
    if x < 0 or x >= n:
        return False
    if x % 2 == 0:
        return 0 <= y < n
    else:
        return 0 <= y < n-1


def go(x, y, nx, ny):
    if x == nx:
        if y < ny:
            return a[x][y][1] == a[nx][ny][0]
        else:
            return a[x][y][0] == a[nx][ny][1]
    else:
        if x%2 == 0:
            if y == ny:
                return a[x][y][1] == a[nx][ny][0]
            else:
                return a[x][y][0] == a[nx][ny][1]
        else:
            if y == ny:
                return a[x][y][0] == a[nx][ny][1]
            else:
                return a[x][y][1] == a[nx][ny][0]

def num(x, y):
    ans = x//2*(n*2-1)
    if x%2 == 1:
        ans += n
    ans += y+1
    return ans

for i in range(n):
    lim = n if i%2 == 0 else n-1
    for j in range(lim):
        a[i].append(tuple(map(int, input().split())))

while q:
    x, y = q.popleft()
    for k in range(6):
        nx, ny = x+dx[x%2][k], y+dy[x%2][k]
        if not ok(nx, ny):
            continue
        if not go(x, y, nx, ny):
            continue
        if check[nx][ny]:
            continue
        check[nx][ny] = True
        via[nx][ny] = (x, y)
        dist[nx][ny] = dist[x][y] + 1
        q.append((nx, ny))

x = y = n-1

while not check[x][y]: # 마지막 타일 찾는 코드
    y -= 1
    if y < 0:
        x -= 1
        y = n-1
        if x%2 == 1:
            y -= 1

print(dist[x][y])
s = []
while not (x == 0 and y == 0):
    s.append((x, y))
    x, y = via[x][y]
s.append((x, y))

while s:
    print(num(*s[-1]), end=' ')
    s.pop()
print()