import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] num = new int[6];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 6; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        long result = 0;

        int a = num[0];
        int b = num[1];
        int c = num[2];
        int d = num[3];
        int e = num[4];
        int f = num[5];

        int[] mins = new int[3];
        long[] count = new long[3];

        mins[0] = Math.min(a, f);
        mins[1] = Math.min(b, e);
        mins[2] = Math.min(c, d);

        Arrays.sort(mins);
        Arrays.sort(num);

        count[0] = 4L;
        count[1] = 8L * (n-2) + 4;
        count[2] = 5L * (n-2) * (n-2) + 4L * (n-2);

        if (n == 1) {
            result += num[0] + num[1] + num[2] + num[3] + num[4];
        } else {
            result += count[0] * (mins[0] + mins[1] + mins[2]);
            result += count[1] * (mins[0] + mins[1]);
            result += count[2] * mins[0];
        }

        System.out.println(result);
    }
}