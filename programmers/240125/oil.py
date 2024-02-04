# 프로그래머스 석유 시추 lv.2

from collections import deque

def solution(land):

    def dfs(start, land_map, total):
        move = [(1,0),(-1,0),(0,-1),(0,1)]
        q = deque([start])
        visted_x_set = set()
        cnt = 0

        while q:
            y, x = q.popleft()
            visted_x_set.add(x)
            cnt += 1

            for m in move:
                dy, dx = y, x
                dy -= m[0]
                dx -= m[1]

                if 0 <= dy < len(land_map) and 0 <= dx < len(land_map[0]):
                    if not (dy,dx) in total and land_map[dy][dx] == 1:
                        q.append((dy,dx))
                        total.add((dy,dx))

        return visted_x_set, cnt, total

    total = set()
    oil_bulks = [0 for _ in range(len(land[0]))]

    for j in range(len(land[0])):
        for i in range(len(land)):
            if land[i][j] == 1 and not (i,j) in total:
                total.add((i,j))
                x, cnt, total = dfs((i,j), land, total)

                for _x in x:
                    oil_bulks[_x] += cnt

    return max(oil_bulks)