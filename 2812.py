n, k = map(int, input().split())
number = input()

stack = []
remain = k

for num in number:
  while stack and remain > 0 and stack[-1] < num:
    stack.pop()
    remain -= 1
  stack.append(num)

for _ in range(remain):
  stack.pop()  

print(''.join(stack))
