from typing import List

class Solution:
    def combinationSum(self, candidates: List[int], target: int) -> List[List[int]]:
        def backtrack(remain, combo, start):
            if remain == 0:
                # 합이 target과 같아지면 결과에 추가
                result.append(list(combo))
                return
            elif remain < 0:
                # 합이 target을 초과하면 더 이상 탐색 x
                return
            
            for i in range(start, len(candidates)):
                # 현재 숫자를 조합에 추가하고 재귀적으로 탐색
                combo.append(candidates[i])
                backtrack(remain - candidates[i], combo, i) # 같은 숫자를 다시 사용할 수 있으므로 i부터 시작
                combo.pop()

        result = []
        backtrack(target, [], 0)
        return result