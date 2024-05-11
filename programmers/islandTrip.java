import java.util.*;

class Solution {
    public int[] solution(String[] maps) {
        int n = maps.length;
        int m = maps[0].length();

        int[] dy = {0, 0, -1, 1};
        int[] dx = {1, -1, 0, 0};
        boolean[][] visited = new boolean[n][m];

        List<Integer> islands = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j] || maps[i].charAt(j) == 'X') continue;

                Queue<int[]> queue = new LinkedList<>();
                queue.offer(new int[]{i, j});
                visited[i][j] = true;

                int days = 0;

                while (!queue.isEmpty()) {
                    int size = queue.size();
                    for (int k = 0; k < size; k++) {
                        int[] cur = queue.poll();
                        int x = cur[0];
                        int y = cur[1];

                        days += maps[x].charAt(y) - '0';

                        for (int l = 0; l < 4; l++) {
                            int nx = x + dx[l];
                            int ny = y + dy[l];

                            if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny] && maps[nx].charAt(ny) != 'X') {
                                queue.offer(new int[]{nx, ny});
                                visited[nx][ny] = true;
                            }
                        }
                    }
                }

                islands.add(days);
            }
        }

        if (islands.isEmpty()) return new int[]{-1};

        Collections.sort(islands);
        return islands.stream().mapToInt(Integer::intValue).toArray();
    }
}