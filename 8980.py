n, c = map(int, input().split())
m = int(input())
boxes = [list(map(int, input().split())) for _ in range(m)]

# 박스를 받는 마을번호 기준으로 오름차순 정렬
boxes.sort(key=lambda x: x[1])

# 각 마을별 현재 트럭에 실린 박스 수
truck = [0] * (n + 1)
total = 0  # 전체 배송된 박스 수

for i in range(m):
  temp = c  # 현재 마을에서 트럭에 실을 수 있는 최대 박스 수
  for j in range(boxes[i][0], boxes[i][1]):
    temp = min(temp, c - truck[j])
  temp = min(temp, boxes[i][2])

  for j in range(boxes[i][0], boxes[i][1]):
    truck[j] += temp
  total += temp

print(total)
