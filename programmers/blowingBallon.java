class Solution {
    public int solution(int[] a) {
        // 1개만 남을 때까지 풍선 터트림
        // 마지막까지 남을 수 있는 풍선의 개수
        // 번호가 더 작은 풍선을 터트리는 건 최대 1번만 가능
        // 오른쪽, 왼쪽 둘 중 하나보다 작다면 가능
        int answer = 0;
        int n = a.length;

        // 왼쪽의 최솟값 계산
        int[] leftMin = new int[n];
        leftMin[0] = a[0];
        for (int i = 1; i < n; i++) {
            leftMin[i] = Math.min(leftMin[i-1], a[i]);
        }

        // 오른쪽의 최솟값 계산
        int[] rightMin = new int[n];
        rightMin[n-1] = a[n-1];
        for (int i = n-2; i >= 0; i--) {
            rightMin[i] = Math.min(rightMin[i+1], a[i]);
        }

        for (int i = 0; i < n; i++) {
            if (a[i] <= leftMin[i] || a[i] <= rightMin[i]) {
                answer++;
            }
        }

        return answer;
    }
}