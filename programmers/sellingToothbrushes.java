import java.util.Map;
import java.util.HashMap;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];

        Map<String, String> parentMap = new HashMap<>();
        Map<String, Integer> indexMap = new HashMap<>();

        for (int i = 0; i < enroll.length; i++) {
            parentMap.put(enroll[i], referral[i]);
            indexMap.put(enroll[i], i);
        }

        for (int i = 0; i < seller.length; i++) {

            String now = seller[i];
            int profit = amount[i] * 100;

            while(!now.equals("-")) {

                int profitForParent = profit / 10;
                int updatedProfit = profit - profitForParent;

                answer[indexMap.get(now)] += updatedProfit;

                // 이익을 부모에게 넘길 금액으로 초기화
                now = parentMap.get(now);
                profit /= 10;

                if (profit < 1) {
                    break;
                }

            }
        }

        return answer;
    }
}
