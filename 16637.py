def calc(op, a, b):
  if op == '+':
    return a+b
  elif op == '-':
    return a-b
  elif op == '*':
    return a*b
  
def dfs(idx, cur_val):
  global max_val
  if idx >= n:
    max_val = max(max_val, cur_val)
    return
  # 괄호를 추가하지 않는 경우
  next_val = calc(str[idx], cur_val, int(str[idx+1]))
  dfs(idx+2, next_val)

  # 괄호를 추가하는 경우 - 어차피 맨 처음은 괄호를 추가하나 마나 상관없으므로 idx + 2 부터
  if idx + 2 < n:
    # 다음 연산자의 수를 먼저 계산
    bracket_val = calc(str[idx+2], int(str[idx+1]), int(str[idx+3]))
    # 이전 결과와 괄호 안의 계산 결과를 연산
    next_val = calc(str[idx], cur_val, bracket_val)
    dfs(idx+4, next_val)

n = int(input())
str = input()
max_val = -2**31
dfs(1, int(str[0]))
print(max_val)
