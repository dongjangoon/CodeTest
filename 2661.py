import sys

n = int(input())

# 조건을 이용하여 백트래킹(재귀)

def check(now):
    m = len(now)
    
    for i in range(1, int((m+2)/2)):
        if now[-i:] == now[-2*i:-i]:
            return False
    
    return True

def dfs(now):
    if not check(now):
        return ""

    if len(now) == n:
        print(now)
        sys.exit(0)
    
    if now[-1] == '1':
        dfs(now+"2")
        dfs(now+"3")
    elif now[-1] == '2':
        dfs(now+"1")
        dfs(now+"3")
    else:
        dfs(now+"1")
        dfs(now+"2")

dfs("1")

