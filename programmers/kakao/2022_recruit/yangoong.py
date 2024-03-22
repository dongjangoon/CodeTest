def solution(n, info):
    global answer, max_gap

    # k점을 더 많이 맞힌 선수가 k점 획득
    # 같으면 어피치가, 아무도 못 맞히면 0
    # 최종 점수가 같으면 어피치가 우승

    # 화살을 아예 안 맞추거나 어피치보다 1점 더 높게 
    # 가장 점수 차이가 큰 것이 답인데 여러 가지가 있으면 낮은 점수로 출력, 없으면 -1
    def calculate(score):
        apeach = 0
        lion = 0

        for i in range(len(info)):
            if score[i] > 0 or info[i] > 0:
                if score[i] > info[i]:
                    lion += (10 - i)
                else:
                    apeach += (10 - i)
        return (lion > apeach, lion - apeach)




    def dfs(depth, cnt):
        global answer, max_gap
        if cnt == 0 or depth == 11:
            is_win, gap = calculate(score)
            if is_win:
                if cnt >= 0:  # 화살이 남은 경우, 0으로
                    score[10] = cnt

                if gap > max_gap:
                    max_gap = gap
                    answer = score[:]

                elif gap == max_gap:  # 가장 낮은 점수를 많이 맞힌 경우로 업데이트
                    for i in range(len(score)):
                        if answer[i] > 0:
                            i1 = i
                        if score[i] > 0:
                            i2 = i
                    if i2 > i1:
                        answer = score[:]
            return



        # k점을 어피치보다 많이 맞추거나 아예 안 맞추거나
        if cnt > info[depth]:
            score[depth] = info[depth] + 1
            dfs(depth + 1, cnt - (info[depth]+1))
            score[depth] = 0

        dfs(depth + 1, cnt)

    max_gap = 0
    answer = [-1]
    score = [0] * 11
    dfs(0, n)

    return answer
