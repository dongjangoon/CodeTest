c, p = map(int, input().split())
a = list(map(int, input().split()))

def calc(i, s):
    if i+len(s) > c: # 가로가 넘어가면 0 리턴
        return 0
    base = a[i] - (ord(s[0])-ord('0')) # 처음 놓은 곳의 높이
    for j in range(len(s)): # 떠 있는지 보는 거 # 가로로 갈수록
        if base != a[i+j] - (ord(s[j])-ord('0')):
            return 0
    return 1


ans = 0
for i in range(c):
    if p == 1:
        ans += calc(i, "0") + calc(i, "0000") # 한칸 뜨면 1 안뜨면 0
    elif p == 2:
        ans += calc(i, "00")
    elif p == 3:
        ans += calc(i, "001") + calc(i, "10")
    elif p == 4:
        ans += calc(i, "100") + calc(i, "01")
    elif p == 5:
        ans += calc(i, "000") + calc(i, "101") + calc(i, "01") + calc(i, "10")
    elif p == 6:
        ans += calc(i, "000") + calc(i, "20") + calc(i, "00") + calc(i, "011")
    elif p == 7:
        ans += calc(i, "000") + calc(i, "02") + calc(i, "110") + calc(i, "00")
print(ans)