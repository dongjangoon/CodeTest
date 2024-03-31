from collections import deque

def solution(bridge_length, weight, truck_weights):
    time = cur_weight = 0
    bridge = deque([0] * bridge_length)
    truck_weights.reverse()
    
    while 0 < len(truck_weights):
        # 매초마다 반복문 수행
        time += 1
        cur_weight -= bridge.popleft()
        
        # 트럭이 올라갈 수 있으면 추가 아니면 0을 추가
        if cur_weight + truck_weights[-1] <= weight:
            cur_weight += truck_weights[-1]
            bridge.append(truck_weights.pop())
        else:
            bridge.append(0)
    
    # 마지막 트럭이 지나는 시간
    time += bridge_length
    return time