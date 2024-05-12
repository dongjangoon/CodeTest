import java.util.*;

class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        // 간선 그래프
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());

        // 간선 그래프에 추가
        for (int[] road: roads) {
            int a = road[0];
            int b = road[1];

            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        // 최단 거리 구하기 - 목적지 기준으로 다익스트라
        int[] distances = new int[n+1];
        Arrays.fill(distances, Integer.MAX_VALUE);  // 거리는 무한대로 초기화
        distances[destination] = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(o -> distances[o]));

        // 우선순위 큐에 출발지 추가
        pq.offer(destination);

        while (!pq.isEmpty()) {
            int cur = pq.poll();

            for (int i = 0; i < graph.get(cur).size(); i++) {
                int nextNode = graph.get(cur).get(i);
                if (distances[nextNode] > distances[cur] + 1) {
                    distances[nextNode] = distances[cur] + 1;
                    pq.offer(nextNode);
                }
            }
        }

        // 결과 배열
        int[] answer = new int[sources.length];

        for (int i = 0; i < sources.length; i++) {
            answer[i] = distances[sources[i]] == Integer.MAX_VALUE ? -1 : distances[sources[i]];
        }

        return answer;
    }
}