public class Solution {
    public long solution(int n, int m, int x, int y, int[][] queries) {
        // 시작점의 행과 열의 범위 초기화
        long startRow = x, endRow = x;
        long startCol = y, endCol = y;

        // 쿼리를 역순으로 순회
        for (int i = queries.length - 1; i >= 0; i--) {
            int direction = queries[i][0];
            int distance = queries[i][1];

            // 쿼리 역방향 적용
            switch (direction) {
                case 0: // 열 감소 (query(0, dx))
                    if (startCol != 0) startCol += distance;
                    endCol = Math.min(endCol + distance, m-1);
                    if (startCol >= m) return 0;
                    break;
                case 1: // 열 증가 (query(1, dx))
                    if (endCol != m-1) endCol -= distance;
                    startCol = Math.max(startCol - distance, 0);
                    if (endCol < 0) return 0;
                    break;
                case 2: // 행 감소 (query(2, dx))
                    if (startRow != 0) startRow += distance;
                    endRow = Math.min(endRow + distance, n-1);
                    if (startRow >= n) return 0;
                    break;
                case 3: // 행 증가 (query(3, dx))
                    if (endRow != n-1) endRow -= distance;
                    startRow = Math.max(startRow - distance, 0);
                    if (endRow < 0) return 0;
                    break;
            }
        }

        // 가능한 시작점의 개수
        long result = (endRow - startRow + 1) * (endCol - startCol + 1);
        return result;
    }
}