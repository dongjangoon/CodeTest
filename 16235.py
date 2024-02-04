from collections import deque

# nxn 땅, m개의 나무, k년
n, m, k = map(int, input().split())

fertilizers = [list(map(int, input().split())) for _ in range(n)] # 겨울마다 추가되는 양분 양
trees = [[deque() for _ in range(n)] for _ in range(n)] # 각 칸마다 deque, 나이가 어린 나무가 앞으로 들어감
land = [[5] * n for _ in range(n)] # 양분 배열
dead_trees = [[list() for _ in range(n)] for _ in range(n)] # 각 칸에서 죽는 나무들

for _ in range(m):
  x, y, age = map(int, input().split())
  trees[x-1][y-1].append(age)

dx = [1, -1, 0, 0, 1, 1, -1, -1]
dy = [0, 0, 1, -1, 1, -1, 1, -1]

def spring_summer():
  for i in range(n):
    for j in range(n):
      # 현재 위치의 나무 개수
      _len = len(trees[i][j])
      for k in range(_len):
        age = trees[i][j][k]
        if land[i][j] >= age:
          land[i][j] -= age
          trees[i][j][k] += 1
        else:
          # 죽는 나무들 다 빼고 저장
          for _ in range(k, _len):
            dead_trees[i][j].append(trees[i][j].pop())
          break

  # 양분 저장
  for i in range(n):
    for j in range(n):
      while dead_trees[i][j]:
        land[i][j] += dead_trees[i][j].pop() // 2
      
def fall_winter():
  for i in range(n):
    for j in range(n):
      # 번식
      _len = len(trees[i][j])
      for k in range(_len):
        age = trees[i][j][k]
        if age % 5 == 0:
          for l in range(8):
            nx, ny = i + dx[l], j + dy[l]
            if 0 <= nx < n and 0 <= ny < n:
              trees[nx][ny].appendleft(1)
      # 양분 주기
      land[i][j] += fertilizers[i][j]

answer = 0

for year in range(k):
  spring_summer()
  fall_winter()

for i in range(n):
  for j in range(n):
    answer += len(trees[i][j])

print(answer)
