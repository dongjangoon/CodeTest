def solution(s):
    answer = ""
    current_word = ""

    keys = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9']
    values = ['zero', 'one', 'two', 'three', 'four', 'five', 'six', 'seven',
              'eight', 'nine']

    for char in s:
        if char in keys:
            answer += char
            continue

        current_word += char

        if current_word in values:
            answer += keys[values.index(current_word)]
            current_word = ""
            continue

    return int(answer)

# opt
num_dic = {"zero":"0", "one":"1", "two":"2", "three":"3", "four":"4", "five":"5", "six":"6", "seven":"7", "eight":"8", "nine":"9"}

def solution(s):
    answer = s
    for key, value in num_dic.items():
        answer = answer.replace(key, value)
    return int(answer)