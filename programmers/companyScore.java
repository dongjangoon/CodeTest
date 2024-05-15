import java.util.Arrays;

class Solution {
    public int solution(int[][] scores) {
        int n = scores.length;
        int[] wanhoScore = scores[0];

        // 근무 태도는 내림차순으로, 동차일 때 동료 평가 점수의 오름차순으로
        Arrays.sort(scores, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return b[0] - a[0];
        });

        int maxScore = scores[0][1];

        for (int i = 1; i < n; i++) {
            if (scores[i][1] < maxScore) { // 인센티브 받지 못하는 경우
                if (scores[i][0] == wanhoScore[0] && scores[i][1] == wanhoScore[1]) return -1; // 완호인 경우
                scores[i][0] = -1;
                scores[i][1] = -1;
            } else {
                maxScore = scores[i][1];
            }
        }

        // 합 내림차순으로 정렬
        Arrays.sort(scores, (a, b) -> {
            return (b[0] + b[1]) - (a[0] + a[1]);
        });

        int wanhoRank = 1;

        for (int i = 0; i < n; i++) {
            if (scores[i][0] + scores[i][1] > wanhoScore[0] + wanhoScore[1]) {
                wanhoRank++;
            } else {
                break;
            }
        }

        return wanhoRank;
    }
}