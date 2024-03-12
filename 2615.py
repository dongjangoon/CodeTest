def check_winner(board):
  dx = [1, 0, 1, 1]  # 우, 아래, 우하향, 우상향
  dy = [0, 1, 1, -1]

  for x in range(19):
    for y in range(19):
      if board[x][y] != 0:
        for k in range(4):
          nx, ny = x + dx[k], y + dy[k] 
          count = 1

          while 0 <= nx < 19 and 0 <= ny < 19 and board[nx][ny] == board[x][y]:
            nx += dx[k]
            ny += dy[k]
            count += 1

          if count == 5:
            # 6목 방지
            prev_x, prev_y = x - dx[k], y - dy[k]
            next_x, next_y = nx, ny
            if not (0 <= prev_x < 19 and 0 <= prev_y < 19 and board[prev_x][prev_y] == board[x][y]) and not (0 <= next_x < 19 and 0 <= next_y < 19 and board[next_x][next_y] == board[x][y]):
              # 우상향 대각선 방향의 경우 시작 위치 조정
              if k == 3:
                return board[x][y], x+count-1+1, y-count+1+1
              else:
                return board[x][y], x+1, y+1
  return 0, 0, 0

board = [list(map(int, input().split())) for _ in range(19)]

winner, x, y = check_winner(board)

print(winner)
if winner != 0:
  print(x, y)

