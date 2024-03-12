n, m, k = map(int, input().split())
fireballs = [list(map(int, input().split())) for _ in range(m)]

dx = [-1, -1, 0, 1, 1, 1, 0, -1]
dy = [0, 1, 1, 1, 0, -1, -1, -1]

for _ in range(k):
  # 파이어볼 이동 정보를 저장할 배열
  temp = [[[] for _ in range(n)] for _ in range(n)]
  for x, y, m, s, d in fireballs:
    nx, ny = (x + dx[d] * s) % n, (y + dy[d] * s) % n
    temp[nx][ny].append([m, s, d])

  # 새로운 파이어볼
  new_fireballs = []
  for r in range(n):
    for c in range(n):
      if len(temp[r][c]) > 1:
        total_m, total_s, even, odd = 0, 0, 0, 0
        for m, s, d in temp[r][c]:
          total_m += m
          total_s += s
          if d % 2 == 0:
            even += 1
          else:
            odd += 1
        nm = total_m // 5
        if nm > 0:  # 질량이 0인 파이어볼은 소멸
          ns = total_s // len(temp[r][c])
          if even == 0 or odd == 0:
            nds = [0, 2, 4, 6]
          else:
            nds = [1, 3, 5, 7]
          for nd in nds:
            new_fireballs.append([r, c, nm, ns, nd])
      # 파이어볼이 하나인 경우
      elif len(temp[r][c]) == 1:
        new_fireballs.append([r, c] + temp[r][c][0])

  fireballs = new_fireballs

answer = sum([m for r, c, m, s, d in fireballs])
print(answer)
