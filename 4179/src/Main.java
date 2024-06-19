import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static int[] dx = new int[]{1, -1, 0, 0};
    public static int[] dy = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        char[][] maze = new char[r][c];
        int[][] fireTime = new int[r][c];
        int[][] jihunTime = new int[r][c];

        Queue<Node> jihunQueue = new LinkedList<>();
        Queue<Node> fireQueue = new LinkedList<>();

        for (int i = 0; i < r; i++) {
            String line = br.readLine();
            for (int j = 0; j < c; j++) {
                maze[i][j] = line.charAt(j);
                fireTime[i][j] = Integer.MAX_VALUE;
                jihunTime[i][j] = Integer.MAX_VALUE;

                if (maze[i][j] == 'J') {
                    jihunQueue.add(new Node(i, j));
                    jihunTime[i][j] = 0;
                } else if (maze[i][j] == 'F') {
                    fireQueue.add(new Node(i, j));
                    fireTime[i][j] = 0;
                }
            }
        }

        // 불 확산 시간 계산
        while (!fireQueue.isEmpty()) {
            Node node = fireQueue.poll();
            int x = node.x, y = node.y;

            for (int k = 0; k < 4; k++) {
                int newX = x + dx[k];
                int newY = y + dy[k];

                if (newX >= 0 && newX < r && newY >= 0 && newY < c && maze[newX][newY] == '.' && fireTime[newX][newY] == Integer.MAX_VALUE) {
                    fireQueue.add(new Node(newX, newY));
                    fireTime[newX][newY] = fireTime[x][y] + 1;
                }
            }
        }

        // 지훈의 탈출 시간
        while (!jihunQueue.isEmpty()) {
            Node node = jihunQueue.poll();
            int x = node.x, y = node.y;

            for (int k = 0; k < 4; k++) {
                int newX = x + dx[k];
                int newY = y + dy[k];

                if (newX < 0 || newX >= r || newY < 0 || newY >= c) {
                    System.out.println(jihunTime[x][y] + 1);
                    return;
                }

                if (maze[newX][newY] == '.' && jihunTime[newX][newY] == Integer.MAX_VALUE && jihunTime[x][y] + 1 < fireTime[newX][newY]) {
                    jihunTime[newX][newY] = jihunTime[x][y] + 1;
                    jihunQueue.add(new Node(newX, newY));
                }
            }
        }

        System.out.println("IMPOSSIBLE");
    }
}

class Node {
    int x, y;

    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}