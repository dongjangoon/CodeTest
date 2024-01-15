def ccw(x, y, z):
  x1, y1 = x
  x2, y2 = y
  x3, y3 = z
  s = (x1*y2 + x2*y3 + x3*y1) - (x2*y1 + x3*y2 + x1*y3)

  if s > 0: return 1
  elif s == 0: return 0
  else: return -1

a, b, c, d = map(int, input().split())
p1 = (a, b)
p2 = (c, d)

a, b, c, d = map(int, input().split())
p3 = (a, b)
p4 = (c, d)

s1 = ccw(p1, p2, p3) * ccw(p1, p2, p4)  # L1 기준
s2 = ccw(p3, p4, p1) * ccw(p3, p4, p2)  # L2 기준

answer = 0
one_line = False

if s1 == 0 and s2 == 0:
  one_line = True
  if min(p1[0], p2[0]) <= max(p3[0], p4[0]) and min(p3[0], p4[0]) <= max(p1[0], p2[0]) \
  and min(p1[1], p2[1]) <= max(p3[1], p4[1]) and min(p3[1], p4[1]) <= max(p1[1], p2[1]):
    answer = 1

if s1 <= 0 and s2 <= 0:
  if not one_line:
    answer = 1

print(answer)
