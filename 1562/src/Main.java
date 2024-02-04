import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int MOD = 1000000000;

        long[][][] d = new long[101][10][1 << 10];

        // 1자리수는 1~9까지
        for (int i = 1; i < 10; i++) {
            d[1][i][1 << i] = 1;
        }

        for (int i = 2; i < 101; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 1024; k++) {
                    int bit = k | (1 << j);
                    if (j == 0) { // 0이 올 때는 다음 수는 무조건 1
                        d[i][j][bit] = (d[i][j][bit] + d[i-1][j+1][k]) % MOD;
                    } else if (j == 9) { // 9가 올 때는 다음 수 무조건 8
                        d[i][j][bit] = (d[i][j][bit] + d[i-1][j-1][k]) % MOD;
                    } else {
                        d[i][j][bit] = (d[i][j][bit] + d[i-1][j+1][k] + d[i-1][j-1][k]) % MOD;
                    }
                }
            }
        }

        long ans = 0;
        for (int i = 0; i < 10; i++) {
            ans = (ans + d[n][i][1023]) % MOD; // 0~9의 수가 모두 포함된 갯수
        }
        System.out.println(ans);
    }
}
