import sys
sys.setrecursionlimit(10**8)

dx = [1, 0, 0, -1]
dy = [0, -1, 1, 0]
da = ['d', 'l', 'r', 'u']
answer = "z"

def solution(n, m, x, y, r, c, k):
    def dfs(n, m, x, y, r, c, prev, cnt, k):
        global answer
        if k < cnt + abs(x - r) + abs(y - c):
            return
        if x == r and y == c and k == cnt:
            answer = prev
            return
        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if 1 <= nx <= n and 1 <= ny <= m and prev < answer:
                dfs(n, m, nx, ny, r, c, prev + da[i], cnt + 1, k)


    shortest = abs(x - r) + abs(y - c)

    if shortest > k or (k - shortest) % 2 == 1:
        return "impossible"

    dfs(n, m, x, y, r, c, "", 0, k)

    return answer