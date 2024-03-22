from collections import defaultdict

def solution(fees, records):
    # 출차 내역이 없으면 23:59 출차로 간주
    # 초과 시간이 단위 시간으로 나눠 떨어지지 않으면 올림
    answer = []

    # 기본 시간, 비용, 단위 시간, 비용
    t, f, dt, df = fees

    # 자동차 번호를 키로 딕셔너리
    info = defaultdict(list)

    for record in records:
        time_str, num, io = record.split()
        time = int(time_str[:2]) * 60 + int(time_str[3:])
        info[num].append(time)

    for i in info:
        if len(info[i]) % 2 == 1:
            info[i].append(23*60 + 59)

    cars = sorted(info.keys())

    for c in cars:
        money = f
        time = sum(info[c][1::2]) - sum(info[c][0::2])

        if time > t:
            money += ((time - t) // dt) * df
            if (time - t) % dt != 0:
                money += df

        answer.append(money)

    return answer