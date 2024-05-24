import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] arr = new int[n][m];
        int[][] dp = new int[n][m];
        int[][] temp = new int[2][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 초기화
        dp[0][0] = arr[0][0];

        // 첫 번째 행 (왼쪽에서밖에 안됨)
        for (int i = 1; i < m; i++) {
            dp[0][i] = dp[0][i - 1] + arr[0][i];
        }

        for (int i = 1; i < n; i++) {

            // 왼쪽 & 위쪽
            temp[0][0] = dp[i-1][0] + arr[i][0]; // 위에서 오는 경우만 고려
            for (int j = 1; j < m; j++) {
                temp[0][j] = Math.max(temp[0][j-1], dp[i-1][j]) + arr[i][j];  // 위에서 오는 경우와 왼쪽에서 오는 경우 중 큰 것
            }

            // 오른쪽 & 위쪽
            temp[1][m-1] = dp[i-1][m-1] + arr[i][m-1]; // 위에서 오는 경우
            for (int j = m - 2; j >= 0; j--) {
                temp[1][j] = Math.max(temp[1][j+1], dp[i-1][j]) + arr[i][j]; // 오른쪽, 위쪽 중 큰 것
            }

            // 둘 중 최대
            for (int j = 0; j < m; j++) {
                dp[i][j] = Math.max(temp[0][j], temp[1][j]);
            }
        }
        System.out.println(dp[n - 1][m - 1]);
    }
}