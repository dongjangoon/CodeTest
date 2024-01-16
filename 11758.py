p1 = list(map(int, input().split()))
p2 = list(map(int, input().split()))
p3 = list(map(int, input().split()))

def ccw(x, y, z):
  x1, y1 = x
  x2, y2 = y
  x3, y3 = z

  s = (x1*y2 + x2*y3 + x3*y1) - (x2*y1 + x3*y2 + x1*y3)

  if s > 0:
    return 1
  elif s == 0:
    return 0
  else: return -1

print(ccw(p1, p2, p3))
