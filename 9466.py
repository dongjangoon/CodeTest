import sys
sys.setrecursionlimit(10**6)

t = int(input())

def dfs(x):
  global teams
  visited[x] = True
  cycle.append(x)
  number = students[x]

  if visited[number]:
    if number in cycle:
      teams += cycle[cycle.index(number):]
    return
  else:
    dfs(number)

for _ in range(t):
  n = int(input())
  students = [0] + list(map(int, input().split()))
  visited = [True] + [False] * n
  teams = []

  for i in range(1, n+1):
    if visited[i] == False:
      cycle = []
      dfs(i)

  print(n - len(teams))
