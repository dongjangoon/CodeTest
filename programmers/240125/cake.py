# 프로그래머스 롤케이크 자르기 lv.2

from collections import Counter

def solution(toppings):
    answer = 0
    
    # 토핑 종류, topping 딕셔너리로 변경
    X, Y = set(), Counter(toppings)
    x, y = 0, len(Y)
    
    for topping in toppings:
        Y[topping] -= 1
        
        if Y[topping] == 0:
            y-=1
        
        if topping not in X:
            X.add(topping)
            x += 1
        
        if x == y:
            answer += 1
        
        if x > y:
            break
    
    return answer