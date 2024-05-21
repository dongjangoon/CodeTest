class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            long cur = numbers[i];

            // 짝수일 경우, 마지막 비트가 항상 0이므로 1만 더해줌
            if (cur % 2 == 0) {
                answer[i] = cur + 1;
            } else {
                long temp = cur;
                long mask = 1;
                // 처음 나오는 0을 1로, 그 다음 비트를 0으로 바꾸기
                while ((temp & mask) != 0) {
                    mask <<= 1;
                }

                answer[i] = cur + mask - (mask >> 1);
            }
        }

        return answer;
    }
}