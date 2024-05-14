import java.util.*;

class Solution {
    private int findParent(int[] parent, int x) {
        if (parent[x] == x) return x;
        return parent[x] = findParent(parent, parent[x]);
    }

    private void union(int[] parent, int a, int b) {
        a = findParent(parent, a);
        b = findParent(parent, b);
        if (a < b) parent[b] = a;
        else parent[a] = b;
    }

    public int solution(int n, int[][] costs) {
        // 간선을 비용 순으로 정렬
        Arrays.sort(costs, (a, b) -> a[2] - b[2]);

        // 각 노드의 부모 노드 저장
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        int answer = 0;

        // 최소 신장 트리 구성
        for (int[] edge: costs) {
            int a = edge[0];
            int b = edge[1];
            int cost = edge[2];

            // 두 노드의 부모가 같지 않으면 해당 간선 선택
            if (findParent(parent, a) != findParent(parent, b)) {
                union(parent, a, b);
                answer += cost;
            }
        }

        return answer;
    }
}