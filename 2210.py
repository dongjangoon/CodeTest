a = [list(map(int, input().split())) for _ in range(5)]
dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]
s = set()

def go(x, y, ans, len):
    if len == 6:
        s.add(ans)
        return

    for k in range(4):
        nx = x + dx[k]
        ny = y + dy[k]
        if nx >= 0 and nx < 5 and ny >= 0 and ny < 5:
            go(nx, ny, ans*10 + a[nx][ny], len+1)
                        
for x in range(5):
    for y in range(5):
        go(x, y, 0, 0)

print(len(s))
