import math

a = input()
idx = 0

for i in range(math.ceil(len(a)/10)):
    print(a[idx:idx+10])
    idx += 10