import sys

n = int(input())
arr = list(map(int, input().split()))
answer = sys.maxsize
left, right = 0, n-1
answer_left = 0
answer_right = 0

while left < right:
  total = arr[left] + arr[right]

  if abs(total) < answer:
    answer = abs(total)
    answer_left = left
    answer_right = right
  
  if total > 0:
    right -= 1
  elif total < 0:
    left += 1
  else:
    break

print(arr[answer_left], arr[answer_right])
