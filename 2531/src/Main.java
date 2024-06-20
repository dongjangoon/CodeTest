import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] sushi = new int[n];
        int[] count = new int[d+1];

        for (int i = 0; i < n; i++) {
            sushi[i] = Integer.parseInt(br.readLine());
        }

        int maxSushi = 0;
        count[c]++;

        for (int i = 0; i < k; i++) {
            count[sushi[i]]++;
        }

        maxSushi = countSushi(count);

        int i = 0;
        int j = k;

        while (i < n) {
            j = j % n;
            count[sushi[i++]]--;
            count[sushi[j++]]++;

            int x = countSushi(count);

            if (maxSushi < x) {
                maxSushi = x;
            }
        }

        System.out.println(maxSushi);
    }

    public static int countSushi(int[] count) {
        int result = 0;
        for (int j : count) {
            if (j > 0) {
                result++;
            }
        }

        return result;
    }
}