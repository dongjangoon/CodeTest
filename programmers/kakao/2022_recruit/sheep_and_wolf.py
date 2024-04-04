def solution(info, edges):
    answer = []
    visited = [0] * len(info)

    def dfs(sheep, wolf):
        if sheep > wolf:
            answer.append(sheep)
        else:
            return

        for parent, child in edges:
            # 부모는 방문했고 자식은 방문 안한 곳으로 dfs
            if visited[parent] and not visited[child]:
                visited[child] = True

                if info[child] == 0:
                    dfs(sheep + 1, wolf)
                else:
                    dfs(sheep, wolf + 1)

                visited[child] = False

    visited[0] = True
    dfs(1, 0)

    return max(answer)
