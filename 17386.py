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

if s1 < 0 and s2 < 0:
  print(1)
else:
  print(0)
