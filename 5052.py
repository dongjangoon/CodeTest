t = int(input())
answer = []

for _ in range(t):
    n = int(input())
    numbers = []
    for _ in range(n):
        numbers.append(input())
    
    flag = True
    numbers.sort()
    for i in range(n-1):
        if numbers[i+1].startswith(numbers[i]):
            answer.append("NO")
            flag = False
            break
    
    if flag:
      answer.append("YES")

for i in answer:
  print(i)