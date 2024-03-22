def solution(n, k):
    # n을 k진수로 변경
    # 문자열을 순회하면서 다음에 0이 오면 멈춤
    # 해당 수가 소수인지 검사하고, 위 조건에 맞는지 검사
    def is_prime(num):
        if num <= 1:
            return False
        if num <= 3:
            return True

        if num % 2 == 0 or num % 3 == 0:
            return False

        i = 5
        while i * i <= num:
            if num % i == 0 or num % (i + 2) == 0:
                return False
            i += 6

        return True


    answer = 0

    # k진수 nn 생성
    nn = ""
    while n > 0:
        nn = str(n % k) + nn
        n //= k

    numbers = nn.split('0')
    for num in numbers:
        if num == '':
            continue
        if is_prime(int(num)):
            answer += 1

    return answer
