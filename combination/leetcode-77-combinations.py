from typing import List

class Solution:
    def combine(self, n: int, k: int) -> List[List[int]]:
        answer = []
        arr = []
        for i in range(1, n+1):
            arr.append(i)

        def dfs(chosen):
            if len(chosen) == k:
                answer.append(chosen[:])
                return
            
            start = arr.index(chosen[-1]) + 1 if chosen else 0
            for nxt in range(start, len(arr)):
                chosen.append(arr[nxt])
                dfs(chosen)
                chosen.pop()
        dfs([])
        return answer
    
    def combine2(self, n: int, k: int) -> List[List[int]]:
        answer = []
        arr = list(range(1, n+1))

        def dfs(chosen, idx, answer):
            if len(chosen) == k:
                answer += [chosen[:]]
                return
            
            for i in range(idx, n):
                chosen += [arr[i]]
                idx += 1

                dfs(chosen, idx, answer)

                chosen.pop()
                idx = i+1

        dfs([], 0, answer)
        return answer