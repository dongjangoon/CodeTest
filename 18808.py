def rotate(sticker):
  # zip이 각 행의 동일한 인덱스 요소들을 튜플로 묶어서 반환, 즉 시계방향 90도 회전
  return list(zip(*sticker[::-1]))


def can_attach(x, y, sticker, board, n, m):
  for i in range(len(sticker)):
    for j in range(len(sticker[0])):
      if sticker[i][j] == 1 and board[x+i][y+j] == 1:
        return False
  return True


def attach(x, y, sticker, board):
  for i in range(len(sticker)):
    for j in range(len(sticker[0])):
      if sticker[i][j] == 1:
        board[x+i][y+j] = 1


n, m, k = map(int, input().split())
board = [[0] * m for _ in range(n)]
result = 0

for _ in range(k):
  r, c = map(int, input().split())
  sticker = [list(map(int, input().split())) for _ in range(r)]
  
  for _ in range(4):
    if len(sticker) <= n and len(sticker[0]) <= m:
      attached = False
      for x in range(n - len(sticker) + 1):
        for y in range(m - len(sticker[0]) + 1):
          if can_attach(x, y, sticker, board, n, m):
            attach(x, y, sticker, board)
            result += sum(row.count(1) for row in sticker)
            attached = True
            break
        if attached:
          break
      if attached:
        break
    sticker = rotate(sticker)

print(result)
