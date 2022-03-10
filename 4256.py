def post(preorder, inorder):
  if len(preorder) == 0:
    return
  if len(preorder) == 1:
    post_order.extend(preorder)
    return

  parents = preorder[0]
  inorder_idx = inorder.index(parents)
  post(preorder[1:inorder_idx+1], inorder[:inorder_idx])
  post(preorder[inorder_idx+1:], inorder[inorder_idx+1:])
  post_order.append(parents)
  
t = int(input())
for _ in range(t):
  n = int(input())
  pre = list(map(int, input().split()))
  in_order = list(map(int, input().split()))
  post_order = []

  post(pre, in_order)
  print(*post_order)
