import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        int[] testcases = new int[t];

        for (int i = 0; i < t; i++) {
            testcases[i] = sc.nextInt();
        }

        long[][] d = new long[65][10];

        for (int i = 0; i < 10; i++) {
            d[1][i] = 1;
        }

        for (int i = 2; i < 65; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = j; k < 10; k++) {
                    d[i][j] += d[i-1][k];
                }
            }
        }

        for (int testcase: testcases) {
            long ans = 0;
            for (int i = 0; i < 10; i++) {
                ans += d[testcase][i];
            }
            System.out.println(ans);
        }
    }
}
