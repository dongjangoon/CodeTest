import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static int[] dx = new int[]{1, 0, -1, 0};
    public static int[] dy = new int[]{0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int count = 0;
        while (true) {
            count++;
            int n = Integer.parseInt(br.readLine());
            if (n == 0) break;

            int[][] cave = new int[n][n];
            int[][] distance = new int[n][n];

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    cave[i][j] = Integer.parseInt(st.nextToken());
                    distance[i][j] = Integer.MAX_VALUE;
                }
            }

            PriorityQueue<Node> pq = new PriorityQueue<>();
            distance[0][0] = cave[0][0];
            pq.offer(new Node(0, 0, cave[0][0]));

            while (!pq.isEmpty()) {
                Node node = pq.poll();
                int curX = node.x;
                int curY = node.y;

                for (int k = 0; k < 4; k++) {
                    int newX = curX + dx[k];
                    int newY = curY + dy[k];

                    if (newX < 0 || newX >= n || newY < 0 || newY >= n) continue;

                    if (distance[newX][newY] > distance[curX][curY] + cave[newX][newY]) {
                        distance[newX][newY] = distance[curX][curY] + cave[newX][newY];
                        pq.offer(new Node(newX, newY, distance[newX][newY]));
                    }
                }
            }

            System.out.printf("Problem %d: %d%n", count, distance[n-1][n-1]);
        }
    }
}

class Node implements Comparable<Node> {
    public int x;
    public int y;
    public int distance;

    public Node(int x, int y, int distance) {
        this.x = x;
        this.y = y;
        this.distance = distance;
    }

    @Override
    public int compareTo(Node o) {
        return this.distance - o.distance;
    }
}