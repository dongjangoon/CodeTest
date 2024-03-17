s = input().strip()
t = input().strip()

# 문자열 뒤에 A를 추가
# 문자열 뒤에 B를 추가 후 뒤집기
# T에서 하나씩 제거하면서 A가 되는지 확인하기

def dfs(t):
  if t == s:
    print(1)
    quit()
  if len(t) == 0:
    return 0
  if t[-1] == 'A':
    dfs(t[:-1])
  if t[0] == 'B':
    dfs(t[1:][::-1])

dfs(t)
print(0)
  