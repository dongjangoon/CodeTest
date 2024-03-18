from collections import deque

n, k = map(int, input().split())
wells = list(map(int, input().split()))

houses = 0  # 집 개수 카운트
unhappiness = 0  # 불행도 계산
queue = deque()
dic = {}  # key: 위치, value: 불행도

for well in wells:
  dic[well] = 0
  queue.append(well)  # 큐에 샘터 위치 모두 저장


def not_well(x):
  if x in dic.keys():
    return False
  return True


# 샘터가 있는 곳 주변을 먼저 보고 그 다음 설치된 집 옆에 집을 늘려가야 하므로 BFS
while queue:
  well = queue.popleft()
  right = well + 1
  left = well - 1

  if not_well(right):
    dic[right] = dic[well] + 1
    houses += 1
    unhappiness += dic[right]
    if houses >= k:
      print(unhappiness)
      exit()
    queue.append(right)
  
  if not_well(left):
    dic[left] = dic[well] + 1
    houses += 1
    unhappiness += dic[left]
    if houses >= k:
      print(unhappiness)
      exit()
    queue.append(left)
