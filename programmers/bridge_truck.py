# 1번째 답안
def solution(bridge_length, weight, truck_weights):
    time = cur_weight = 0
    bridge = [0] * bridge_length
    
    while 0 < len(truck_weights):
        # 매초마다 반복문 수행
        time += 1
        cur_weight -= bridge.pop(0)
        
        # 트럭이 올라갈 수 있으면 추가 아니면 0을 추가
        if cur_weight + truck_weights[0] <= weight:
            cur_weight += truck_weights[0]
            # 리스트에서 0번째 원소를 제거하고 나머지 원소들을 한 칸 씩 당기므로 느림
            bridge.append(truck_weights.pop(0))
        else:
            bridge.append(0)
    
    # 마지막 트럭이 지나는 시간
    time += bridge_length
    return time
