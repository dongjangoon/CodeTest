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

        int[] lectures = new int[n];

        st = new StringTokenizer(br.readLine());
        int maxLength = 0;
        int totalLength = 0;

        for (int i = 0; i < n; i++) {
            lectures[i] = Integer.parseInt(st.nextToken());
            totalLength += lectures[i];
            maxLength = Math.max(maxLength, lectures[i]);
        }

        int left = maxLength;
        int right = totalLength;
        int result = totalLength;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (isPossible(lectures, n, m, mid)) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(result);
    }

    private static boolean isPossible(int[] lectures, int n, int m, int size) {
        int count = 1;
        int sum = 0;

        for (int i = 0; i < n; i++) {
            if (sum + lectures[i] > size) {
                count++;
                sum = lectures[i];
                if (count > m) {
                    return false;
                }
            } else {
                sum += lectures[i];
            }
        }

        return true;
    }
}