# r개의 순열을 구하는 경우
def get_permutation(arr, r):
    # 1
    arr = sorted(arr) # 정렬은 출력을 이쁘게 보이기 위함
    used = [0 for _ in range(len(arr))] # i번째 값을 썼는지 저장하는데 사용

    def generate(chosen, used):
        # 2. chosen은 순열의 원소를 저장, 개수가 r이 되는 순간 출력
        if len(chosen) == r:
            print(chosen)
            return
        
        # 3. chosen에 값 추가 후, used에 해당 변수를 사용했다고 체크. 다 만들어지면 uncheck가 됨
        for i in range(len(arr)):
            if not used[i]:
                chosen.append(arr[i])
                used[i] = 1
                generate(chosen, used)
                used[i] = 0
                chosen.pop()
    generate([], used)

nums = [1, 2, 3]

get_permutation(nums, len(nums))
