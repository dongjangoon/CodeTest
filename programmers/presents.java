import java.util.Map;
import java.util.HashMap;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        int n = friends.length;
        Map<String, Integer> match = new HashMap<>();
        int[] giftIndex = new int[n];
        int[][] giftCounts = new int[n][n];

        for (int i = 0; i < friends.length; i++) {
            match.put(friends[i], i);
        }

        for (String gift: gifts) {
            String[] tokens = gift.split(" ");
            String giver = tokens[0];
            String receiver = tokens[1];

            // 선물 지수 계산
            giftIndex[match.get(giver)]++;
            giftIndex[match.get(receiver)]--;

            // 주고 받은 선물 수 계산
            giftCounts[match.get(giver)][match.get(receiver)]++;
        }

        for (int i = 0; i < n; i++) {
            int max = 0;
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                if (giftCounts[i][j] > giftCounts[j][i] ||
                        (giftCounts[i][j] == giftCounts[j][i] && giftIndex[i] > giftIndex[j])) {
                    max++;
                }
            }

            if (answer < max) {
                answer = max;
            }
        }

        return answer;
    }
}