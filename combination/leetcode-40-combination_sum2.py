from typing import List

class Solution:
    def combinationSum2(self, candidates: List[int], target: int) -> List[List[int]]:
        def backtrack(remain, combo, start):
            if remain == 0:
                answer.append(combo[:])
                return
            if remain < 0:
                return
            
            for i in range(start, len(candidates)):
                if i > start and candidates[i] == candidates[i-1]:
                    continue

                combo.append(candidates[i])
                backtrack(remain - candidates[i], combo, i+1)
                combo.pop()

        candidates.sort()
        answer = []
        backtrack(target, [], 0)
        return answer