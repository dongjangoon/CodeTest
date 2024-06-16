import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        List<Integer> results = new ArrayList<>();

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int[] applicants = new int[n+1];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                applicants[a] = b;
            }

            int minInterviewRank = applicants[1];
            int maxHires = 1;

            for (int i = 2; i < applicants.length; i++) {
                if (applicants[i] < minInterviewRank) {
                    maxHires++;
                    minInterviewRank = applicants[i];
                }
            }

            results.add(maxHires);
        }

        for (int result: results) {
            System.out.println(result);
        }
    }
}