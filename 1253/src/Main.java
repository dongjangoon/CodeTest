import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] num = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(num);
        int count = 0;

        for (int k = 0; k < n; k++) {
            int target = num[k];
            int i = 0;
            int j = n-1;

            while (i < j) {
                if (i == k) {
                    i++;
                    continue;
                }
                if (j == k) {
                    j--;
                    continue;
                }

                int sum = num[i] + num[j];

                if (sum == target) {
                    count++;
                    break;
                } else if (sum < target) {
                    i++;
                } else {
                    j--;
                }
            }
        }

        System.out.println(count);
    }
}