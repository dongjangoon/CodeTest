import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int l = 0;
        int r = n-1;
        int best = Integer.MAX_VALUE;
        int bestL = 0;
        int bestR = 0;

        while (l < r) {
            int sum = arr[l] + arr[r];

            if (Math.abs(sum) < Math.abs(best)) {
                best = sum;
                bestL = l;
                bestR = r;
            }

            if (sum < 0) {
                l++;
            } else {
                r--;
            }
        }

        System.out.println(arr[bestL] + " " + arr[bestR]);
    }
}