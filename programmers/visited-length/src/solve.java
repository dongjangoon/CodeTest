public class solve {

    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, -1, 0, 1};

    public int solution(String dirs) {
        int answer = 0;
        int[][] map = new int[11][11];
        boolean[][][] visit = new boolean[11][11][4];
        int r = 5, c = 5;

        for (int i = 0; i < dirs.length(); i++) {
            char cc = dirs.charAt(i);
            int dir = 0;

            if (cc == 'U') dir = 0;
            if (cc == 'L') dir = 1;
            if (cc == 'D') dir = 2;
            if (cc == 'R') dir = 3;

            int nr = r + dr[dir];
            int nc = c + dc[dir];

            if (nr < 0 || nc < 0 || nr >= 11 || nc >= 11) continue;

            if (!visit[nr][nc][dir]) {
                visit[nr][nc][dir] = true;
                dir = (dir%2 == 0) ? 2-dir : 4-dir;
                visit[r][c][dir] = true;
                answer++;
            }
            r = nr;
            c = nc;
        }
        return answer;
    }
}
