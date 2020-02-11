import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int[] houses = new int[n];

        for(int i = 0; i < n; i++) {
            houses[i] = Integer.parseInt(bf.readLine());
        }

        Arrays.sort(houses);

        int max = houses[n - 1] - houses[0];
        int min = 1;
        int result = 1;

        while(max >= min) {
            int mid = (max + min) / 2;
            if(check(houses, mid, c)) {
                result = Math.max(mid, result);
                min = mid + 1;
            }
            else {
                max = mid - 1;
            }
        }

        System.out.println(result);
    }

    private static boolean check(int[] input, int dist, int c) {
        int count = 1;
        int last = input[0] + dist;

        for(int i = 1; i < input.length; i++) {
            if(input[i] >= last) {
                count++;
                last = input[i] + dist;
            }
        }

        return count >= c;
    }
}
