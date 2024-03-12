n, m = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(n)]
moves = [tuple(map(int, input().split())) for _ in range(m)]

dy = [0, -1, -1, 0, 1, 1, 1, 0, -1]
dx = [0, 0, -1, -1, -1, 0, 1, 1, 1]

# 초기 구름 위치
clouds = {(n-1, 0), (n-1, 1), (n-2, 0), (n-2, 1)}

for d, s in moves:
  # 구름 이동
  new_clouds = set()
  for x, y in clouds:
    nx, ny = (x + dx[d] * s) % n, (y + dy[d] * s) % n
    new_clouds.add((nx, ny))
  clouds = new_clouds

  # 비 내리기
  for x, y in clouds:
    board[x][y] += 1

  # 물복사버그
  for x, y in clouds:
    count = 0
    for i in range(2, 9, 2):  # 대각선 방향
      nx, ny = x + dx[i], y + dy[i]
      if 0 <= nx < n and 0 <= ny < n and board[nx][ny] > 0:
        count += 1
    board[x][y] += count

  # 새 구름 생성
  new_clouds = set()
  for x in range(n):
    for y in range(n):
      if board[x][y] >= 2 and (x, y) not in clouds:
        new_clouds.add((x, y))
        board[x][y] -= 2
  clouds = new_clouds

answer = sum(sum(row) for row in board)
print(answer)
