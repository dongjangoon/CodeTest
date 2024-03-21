def solution(users, emoticons):
    # 할인율 10, 20, 30, 40
    # 가입자를 최대한 늘리고 그 다음 판매액을 최대한 늘린다
    # 자신의 기준에 따라 일정 비율 이상 할인하는 이모티콘 모두 구매
    # 이모티콘 구매 합이 일정 가격 이상이 되면, 구매를 모두 취소하고 멤버십 가입

    # 1. 최대한 멤버십 가입을 늘려야하므로 이모티콘 구매
    # 2. 최대한 가격을 늘려야 함

    # -> 일단 이모티콘을 사야 하는 게 중요하므로 이모티콘 별로 할인율을 먼저 조정하면서
    #    멤버십 구매를 하는 최소 할인율을 찾아야 함

    def dfs(tmp, count):
        if count == m:
            discounts.append(tmp[:])
            return
        else:
            for candidate in candidates:
                tmp[count] = candidate
                dfs(tmp, count+1)
                tmp[count] = 0


    answer = [0, 0]
    candidates = [10, 20, 30, 40]
    discounts = []

    n = len(users)
    m = len(emoticons)

    # 모든 할인율 조합 구성
    dfs([0] * m, 0)

    for discount in discounts:
        cnt = 0
        amount = 0

        for user in users:
            amount_by_user = 0
            allow_discount, allow_price = user  # 허용 할인율, 멤버십 구매 금액

            for i in range(m):
                # 허용 할인율보다 할인율이 큰 이모티콘 구입
                if allow_discount <= discount[i]:
                    amount_by_user += emoticons[i] * (100 -discount[i]) / 100

                # 그러다 멤버십 구매를 충족하는 금액이 되면 break
                if amount_by_user >= allow_price:
                    break

            if amount_by_user >= allow_price:
                amount_by_user = 0
                cnt += 1

            amount += amount_by_user

        if cnt > answer[0]:
            answer = [cnt, amount]
        elif cnt == answer[0]:
            answer = [cnt, max(amount, answer[1])]


    return answer
