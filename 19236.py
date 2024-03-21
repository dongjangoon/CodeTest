import copy


def find_fish(fish, board):
  for i in range(4):
    for j in range(4):
      if board[i][j][0] == fish:
        return (i, j)


def get_shark_position(shark_x, shark_y, board):
  positions = []
  _dir = board[shark_x][shark_y][1]

  for i in range(1, 5):
    nx = shark_x + dx[_dir] * i
    ny = shark_y + dy[_dir] * i
    if 0 <= nx < 4 and 0 <= ny < 4 and board[nx][ny][0] != 0:
      positions.append((nx, ny))

  return positions


def move_fish(shark_x, shark_y, board):
  # 번호가 작은 물고기부터 (1 ~ 16)
  for fish in range(1, 17):
    # 물고기 좌표 찾기
    position = find_fish(fish, board)
    
    if position:
      x, y = position
      _dir = board[x][y][1]

      # 반시계 방향으로 45도씩 회전 (최대 360도)
      for i in range(8):
        nd = (_dir + i) % 8
        nx = x + dx[nd]
        ny = y + dy[nd]

        if 0 <= nx < 4 and 0 <= ny < 4:
          if not (nx == shark_x and ny == shark_y):
            # 진행 방향 확정
            board[x][y][1] = nd
            
            # 이동할 칸(빈칸, 물고기)과 자리 교체
            board[nx][ny], board[x][y] = board[x][y], board[nx][ny]
            break


def dfs(shark_x, shark_y, eat, board):
  global max_fish

  # 물고기 잡아먹음
  eat += board[shark_x][shark_y][0]
  max_fish = max(max_fish, eat)
  board[shark_x][shark_y][0] = 0  # 잡아먹힌 거 마킹
  
  # 물고기 이동
  move_fish(shark_x, shark_y, board)

  # 상어의 이동 가능한 좌표 (=물고기 위치) 리턴 받기
  position = get_shark_position(shark_x, shark_y, board)

  if position:
    for shark_nx, shark_ny in position:
      dfs(shark_nx, shark_ny, eat, copy.deepcopy(board))


board = [[None] * 4 for _ in range(4)]

for i in range(4):
  row = list(map(int, input().split()))
  for j in range(4):
    board[i][j] = [row[2*j], row[2*j+1] - 1]

# 방향
dx = [-1, -1, 0, 1, 1, 1, 0, -1]
dy = [0, -1, -1, -1, 0, 1, 1, 1]

max_fish = 0
dfs(0, 0, 0, board)
print(max_fish)
