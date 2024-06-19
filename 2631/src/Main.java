import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static int[] lis;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        // LIS 사용
        lis = new int[n];
        lis[0] = arr[0];
        int i = 1;
        int j = 0;

        while (i < n) {
            if (lis[j] < arr[i]) {
                lis[j+1] = arr[i];
                j++;
            } else {
                int idx = binarySearch(0, j, arr[i]);
                lis[idx] = arr[i];
            }
            i++;
        }

        System.out.println(n-(j+1));
    }

    private static int binarySearch(int l, int r, int x) {
        int mid;

        while (l < r) {
            mid = (l + r) / 2;

            if (lis[mid] < x) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return r;
    }
}