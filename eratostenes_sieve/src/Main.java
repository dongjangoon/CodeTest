import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int n = 50;
        boolean[] isPrime = sieveOfEratosthenes(n);

        for (int i = 0; i < n; i++) {
            if (isPrime[i]) {
                System.out.print(i + " ");
            }
        }
    }

    public static boolean[] sieveOfEratosthenes(int n) {
        // n+1 크기의 배열을 생성하고 모두 true로 초기화합니다.
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);

        // 0과 1은 소수가 아니므로 false로 설정합니다.
        isPrime[0] = false;
        isPrime[1] = false;

        for (int p = 2; p * p <= n; p++) {
            // isPrime[p]가 true라면 p는 소수입니다.
            if (isPrime[p]) {
                // p의 배수들을 false로 설정합니다.
                for (int i = p * p; i <= n; i += p) {
                    isPrime[i] = false;
                }
            }
        }

        return isPrime;
    }
}