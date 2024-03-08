from typing import List

class Solution:
    def permuteUnique(self, nums: List[int]) -> List[List[int]]:
        def backtrack(path, counter):
            if len(path) == len(nums):
                result.append(path[:])
                return
            for num in counter:
                if counter[num] > 0:
                    path.append(num)
                    counter[num] -= 1

                    backtrack(path, counter)

                    path.pop()
                    counter[num] += 1

        # 각 숫자의 등장 횟수를 계산
        counter = {}
        for num in nums:
            if num in counter:
                counter[num] += 1
            else:
                counter[num] = 1
        
        result = []
        backtrack([], counter)
        return result