n = int(input())

# 5로 나눠 떨어지는 경우 바로 계산
if n % 5 == 0:
  print(n//5)
  exit()

# 5의 배수가 될 때까지 2를 빼고 카운트를 증가시킴
answer = 0
while n > 0:
  n -= 2
  answer += 1
  if n % 5 == 0:
    print(answer + (n//5))
    exit()

print(-1)