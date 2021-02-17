x, y = map(int, input().split())
date = ["MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN"]
month = [0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]

a = y-1
for i in range(x):
    a += month[i]

print(date[a%7])

