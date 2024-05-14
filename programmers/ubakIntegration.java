class Solution {
    public double[] solution(int k, int[][] ranges) {
        // n과 그래프의 좌표들 구하기
        int n = 0;
        int copiedK = k;
        while (copiedK > 1) {
            if (copiedK % 2 == 0) {
                copiedK /= 2;
                n++;
            } else {
                copiedK = copiedK * 3 + 1;
                n++;
            }
        }

        // 그래프 배열 생성
        int[] graph = new int[n + 1];
        copiedK = k;
        graph[0] = copiedK;
        for (int i = 1; i <= n; i++) {
            if (copiedK % 2 == 0) {
                copiedK /= 2;
                graph[i] = copiedK;
            } else {
                copiedK = copiedK * 3 + 1;
                graph[i] = copiedK;
            }
        }


        // 순회하면서 구간 구하기
        double[] answer = new double[ranges.length];
        for (int i = 0; i < ranges.length; i++) {
            // 정적분 범위 구하기
            int[] range = ranges[i];
            int a = range[0];
            int b = n + range[1];

            // a가 더 큰 경우
            if (b < a) {
                answer[i] = -1;
                continue;
            }

            // 정적분
            double size = 0;
            for (int x = a; x < b; x++) {
                double curSize = (graph[x] + graph[x+1]) / 2.0;
                size += curSize;
            }

            answer[i] = size;
        }

        return answer;
    }
}