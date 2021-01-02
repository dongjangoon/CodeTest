n, m = map(int, input().split())
a = [list(map(int, input().split())) for _ in range(n)]

def next_permutation(e):
    i = len(e) - 1
    while i > 0 and e[i-1] >= e[i]: # 맨 앞이 [1] * m 개이면 return false
        i -= 1
    if i <= 0:
        return False

    j = len(e) - 1

    while e[i-1] >= e[j]: # e[j] = 맨 뒤의 1
        j -= 1

    e[i-1], e[j] = e[j], e[i-1]

    j = len(e) - 1
    while i < j:
        e[i], e[j] = e[j], e[i]
        i += 1
        j -= 1

    return True


def main():
    people = []
    chickenHouse = []
    for i in range(n):
        for j in range(n):
            if a[i][j] == 1:
                people.append((i, j))
            elif a[i][j] == 2:
                chickenHouse.append((i, j))

    d = [0]*len(chickenHouse) # 뽑을 m개만큼 뒤에서부터 1로 기록
    for i in range(m):
        d[i] = 1
    d.sort()
    ans = -1

    while True:
        result = 0
        for px, py in people:
            dists = []
            for i, (sx, sy) in enumerate(chickenHouse): 
                if d[i] == 1: # 거리 기록
                    dx = abs(sx - px)
                    dy = abs(sy - py)
                    dists.append(dx + dy)
            dists.sort()
            result += dists[0] # 최단 거리
        if ans == -1 or result < ans:
            ans = result
        if not next_permutation(d): # permutation 끝에 다다르면 false
            break

    print(ans)

if __name__ == "__main__":
    main()