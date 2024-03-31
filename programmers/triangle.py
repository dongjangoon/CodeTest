def solution(triangle):
    h = len(triangle)
    dp = [[0] * h for _ in range(h)]
    dp[0][0] = triangle[0][0]
    
    for x in range(1, h):
        for y in range(x+1):
            if x == y:
                dp[x][y] = dp[x-1][y-1] + triangle[x][y]

            elif y == 0:
                dp[x][y] = dp[x-1][y] + triangle[x][y]

            else:    
                dp[x][y] = max(dp[x-1][y], dp[x-1][y-1]) + triangle[x][y]
        
    
    return max(dp[h-1])