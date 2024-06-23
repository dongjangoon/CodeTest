import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        if (n == 1) {
            System.out.println(0);
            return;
        }

        // n 이하의 소수 list
        List<Integer> primesUnderN = eratosthenes(n);
        int result = 0;
        int left = 0, right = 0;
        int sum = 0;

        while (right < primesUnderN.size()) {
            if (sum < n) {
                sum += primesUnderN.get(right++);
            } else if (sum > n) {
                sum -= primesUnderN.get(left++);
            } else {
                result++;
                sum += primesUnderN.get(right++) - primesUnderN.get(left++);
            }
        }

        // 남은 합이 n인 경우 처리
        while (sum >= n && left < primesUnderN.size()) {
            if (sum == n) {
                result++;
            }
            sum -= primesUnderN.get(left++);
        }

        System.out.println(result);
    }

    public static List<Integer> eratosthenes(int n) {
        boolean[] isPrime = new boolean[n+1];
        Arrays.fill(isPrime, true);

        isPrime[0] = false;
        isPrime[1] = false;

        for (int i = 2; i * i <= n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        List<Integer> result = new ArrayList<>();

        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                result.add(i);
            }
        }

        return result;
    }
}