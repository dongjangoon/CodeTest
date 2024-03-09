import heapq

n = int(input())
candidates = []

for _ in range(n):
  s, t = map(int, input().split())
  candidates.append([s, t])

# 강의를 시작 시간 기준으로 정렬
candidates.sort(key=lambda x: x[0])

# 최소 힙 초기화
classrooms = []
heapq.heappush(classrooms, candidates[0][1])

for i in range(1, len(candidates)):
  # 현재 강의의 시작 시간이 가장 먼저 끝나는 강의실의 종료 시간보다 늦거나 같으면, 해당 강의실에서 계속 강의
  if candidates[i][0] >= classrooms[0]:
    heapq.heappop(classrooms) # 가장 먼저 끝나는 강의실의 종료 시간 갱신
  heapq.heappush(classrooms, candidates[i][1]) # 새로운 강의 종료 시간 추가

print(len(classrooms))
