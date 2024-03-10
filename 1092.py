n = int(input())
cranes = list(map(int, input().split()))
m = int(input())
boxes = list(map(int, input().split()))

# 크레인과 박스 내림차순 정렬
cranes.sort(reverse=True)
boxes.sort(reverse=True)

if boxes[0] > cranes[0]:
  print(-1)
else:
  time = 0
  while boxes:
    time += 1
    for crane in cranes:
      for box in boxes:
        if crane >= box:
          boxes.remove(box)
          break
  print(time)
