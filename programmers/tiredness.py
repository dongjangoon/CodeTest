def find_max(idx, cur, dungeons, visited):
    stack = [(idx, cur, visited)]
    max_depth = 0

    while stack:
        idx, cur, visited = stack.pop()

        # 끝에 다다르면 조합 완성
        if idx == len(dungeons):
            max_depth = max(max_depth, idx)
            continue

        for i in range(len(dungeons)):
            if not visited[i] and cur >= dungeons[i][0]:
                new_visited = visited[:]
                new_visited[i] = True
                stack.append((idx + 1, cur - dungeons[i][1], new_visited))

        max_depth = max(max_depth, idx)

    return max_depth

def solution(k, d):
    return find_max(0, k, d, [False] * len(d))
