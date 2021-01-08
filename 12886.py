import sys
sys.setrecursionlimit(1500*1500)

a, b, c = map(int, input().split())
check = [[False]*1501 for _ in range(1501)]
s = a+b+c

def go(x, y):
    if check[x][y]:
        return
    check[x][y] = True
    nums = [x, y, s-x-y]
    for i in range(3):
        for j in range(3):
            if nums[i] < nums[j]:
                nums2 = [x, y, s-x-y]
                nums2[i] += nums[i]
                nums2[j] -= nums[i]
                go(nums2[0], nums2[1])


if s % 3 != 0:
    print(0)
else:
    go(a, b)
    if check[s//3][s//3]:
        print(1)
    else:
        print(0)
