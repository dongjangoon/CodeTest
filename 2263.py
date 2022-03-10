import sys
sys.setrecursionlimit(10**6)

n = int(input())
inorder = list(map(int, input().split()))
postorder = list(map(int, input().split()))

pos = [0] * (n + 1)
for i in range(n):
  pos[inorder[i]] = i

def divide(in_start, in_end, p_start, p_end):
  if (in_start > in_end) or (p_start > p_end):
    return
  
  parents = postorder[p_end]
  print(parents, end=" ")

  left = pos[parents] - in_start  # 루트 기준 왼쪽 갯수
  right = in_end - pos[parents]  # 루트 기준 오른쪽 갯수

  divide(in_start, in_start+left-1, p_start, p_start+left-1)
  divide(in_end-right+1, in_end, p_end-right, p_end-1)

divide(0, n-1, 0, n-1)