import java.util.*;

class Solution {
    private static final int[][] keypad = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9},
            {-1, 0, -1}
    };

    private static final Map<Integer, int[]> positionMap = new HashMap<>();

    static {
        for (int i = 0; i < keypad.length; i++) {
            for (int j = 0; j < keypad[0].length; j++) {
                if (keypad[i][j] != -1) {
                    positionMap.put(keypad[i][j], new int[]{i, j});
                }
            }
        }
    }

    public int solution(String numbers) {
        int n = numbers.length();
        int[][][] dp = new int[n+1][10][10]; // 2, 3차원은 각각 왼손, 오른손 숫자 매칭

        for (int[][] row: dp) {
            for (int[] col: row) {
                Arrays.fill(col, Integer.MAX_VALUE);
            }
        }

        dp[0][4][6] = 0;

        for (int i = 1; i <= n; i++) {
            int num = numbers.charAt(i-1) - '0';

            for (int left = 0; left <= 9; left++) {
                for (int right = 0; right <= 9; right++) {
                    if (dp[i-1][left][right] == Integer.MAX_VALUE) continue;

                    if (left == num || right == num) {
                        dp[i][left][right] = Math.min(dp[i][left][right], dp[i-1][left][right] + 1);
                        continue;
                    }

                    if (left != num) {
                        int leftCost = calculateCost(left, num);
                        dp[i][num][right] = Math.min(dp[i][num][right], dp[i-1][left][right] + leftCost);
                    }

                    if (right != num) {
                        int rightCost = calculateCost(right, num);
                        dp[i][left][num] = Math.min(dp[i][left][num], dp[i-1][left][right] + rightCost);
                    }
                }
            }
        }

        int minCost = Integer.MAX_VALUE;
        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                minCost = Math.min(minCost, dp[n][i][j]);
            }
        }

        return minCost;
    }

    private int calculateCost(int from, int to) {
        int[] fromPos = positionMap.get(from);
        int[] toPos = positionMap.get(to);

        if (fromPos[0] == toPos[0] && fromPos[1] == toPos[1]) return 1;

        int xDiff = Math.abs(fromPos[0] - toPos[0]);
        int yDiff = Math.abs(fromPos[1] - toPos[1]);

        if (fromPos[0] == toPos[0]) {
            return yDiff * 2;
        }

        if (fromPos[1] == toPos[1]) {
            return xDiff * 2;
        }

        if (xDiff == yDiff) {
            return xDiff * 3;
        }

        return Math.min(xDiff, yDiff) * 3 + Math.abs(xDiff - yDiff) * 2;
    }
}