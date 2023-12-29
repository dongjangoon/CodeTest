import sys
sys.setrecursionlimit(10**6)

def dfs(x):
  visited[x] = True
  
  for i in tree[x]:
    if not visited[i]:
      dfs(i)
      dp[x][0] += max(dp[i][0], dp[i][1])
      dp[x][1] += dp[i][0]

n = int(input())
people = [0] + list(map(int, input().split()))
tree = [[] for i in range(n+1)]
visited = [False for i in range(n+1)]
dp = [[0, people[i]] for i in range(n+1)]  # [i][0]: 우수마을 x, [i][1]: 우수마을 o

for i in range(n-1):
  a, b = map(int, input().split())
  tree[a].append(b)
  tree[b].append(a)

dfs(1)
print(max(dp[1][0], dp[1][1]))
