from collections import deque

f, s, g, u, d = map(int, input().split())

a = [0]*(f+1)
check = [False]*(f+1)

q = deque()
q.append(s)
check[s] = True

while q:
    now = q.popleft()
    if now+u <= f and not check[now+u]:
        a[now+u] = a[now] + 1
        check[now+u] = True
        q.append(now+u)
    if now-d >= 1 and not check[now-d]:
        a[now-d] = a[now] + 1
        check[now-d] = True
        q.append(now-d)

if check[g]:
    print(a[g])
else:
    print("use the stairs")