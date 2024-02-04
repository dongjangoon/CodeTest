string = input()
bomb = input()
stack = []
for i in string:
  stack.append(i)
  if i == bomb[-1] and ''.join(stack[-len(bomb):]) == bomb:
    del stack[-len(bomb):]
result = ''.join(stack)

if result == '':
  print('FRULA')
else:
  print(result)
