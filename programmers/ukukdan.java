class Solution {
    public int[] solution(int e, int[] starts) {
        // 각 수의 약수 개수를 저장하는 배열
        int[] divisors = new int[e + 1];

        // 각 수의 약수 개수 계산
        for (int i = 1; i <= e; i++) {
            for (int j = i; j <= e; j += i) {
                divisors[j]++;
            }
        }

        // DP 배열을 이용해 특정 범위 내에서 가장 많이 등장하는 수를 저장
        // 역순으로 진행해서 작은 수가 덮어씌워지도록
        int[] dp = new int[e + 1];
        int maxNumber = e;
        dp[e] = e;

        for (int i = e - 1; i >= 1; i--) {
            if (divisors[i] >= divisors[maxNumber]) {
                maxNumber = i;
            }
            dp[i] = maxNumber;
        }

        // 결과 도출
        int[] answer = new int[starts.length];
        for (int i = 0; i < starts.length; i++) {
            answer[i] = dp[starts[i]];
        }

        return answer;
    }
}