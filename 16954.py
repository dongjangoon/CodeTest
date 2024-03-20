from collections import deque

chess_board = [input() for _ in range(8)]
start = (7, 0)
end = (0, 7)

dx = [0, 0, 0, 1, 1, 1, -1, -1, -1]
dy = [0, 1, -1, 0, 1, -1, 0, 1, -1]

queue = deque([(start[0], start[1], 0)])  # 위치, 시간 저장
visited = set([(end[0], end[1], 0)])

while queue:
  x, y, t = queue.popleft()

  if (x, y) == end:
    print(1)
    exit(0)

  for k in range(9):
    nx, ny = x + dx[k], y + dy[k]
    nt = t + 1

    if 0 <= nx < 8 and 0 <= ny < 8 and (nx, ny, nt) not in visited:
      if 0 <= nx-t < 8 and chess_board[nx-t][ny] == '#':  # 현재 위치에 벽이 있으면 통과
        continue  
      if 0 <= nx-t-1 < 8 and chess_board[nx-t-1][ny] == '#':  # 다음 위치에 벽이 있으면 통과
        continue

      queue.append((nx, ny, nt))
      visited.add((nx, ny, nt))

print(0)
