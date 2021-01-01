n, m, h = map(int, input().split())
horizon = [list(map(int, input().split())) for _ in range(m)]

def check(i, b):
    

def go(a, x, y, hor):
    b = [[False]*n-1 for _ in range(h)]
    for i, j in hor:
        b[i][j] = True/
    for i in range(n):
        if not check(i, b):
            return -1
        
    ans = 3
    for i in range(h):
        for j in range(n-1):
            if a[i][j]:
                continue
            temp = go(a, i, j, hor+[(i, j)])
            if ans > temp:
                ans = temp
    return ans

def main():
    a = [[False]*n-1 for _ in range(h)]
    for x, y in horizon:
        a[x][y] = True

    print(go(a, 0, 0, []))

if __name__ == "__main__":
    main()