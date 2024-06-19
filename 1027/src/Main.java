import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] heights = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }

        // 볼 수 있는 빌딩의 수를 저장하는 행렬
        int[] candidates = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;

                boolean canSee = true;
                double gradient = (double) (heights[j] - heights[i]) / (j -i);

                if (i > j) {
                    for (int k = i-1; k > j; k--) {
                        double expectedHeight = heights[i] + gradient * (k - i);
                        if (heights[k] >= expectedHeight) {
                            canSee = false;
                            break;
                        }
                    }
                } else {
                    for (int k = i+1; k < j; k++) {
                        double expectedHeight = heights[i] + gradient * (k - i);
                        if (heights[k] >= expectedHeight) {
                            canSee = false;
                            break;
                        }
                    }
                }
                if (canSee) candidates[i]++;
            }
        }

        int maxVisible = 0;
        for (int count: candidates) {
            if (count > maxVisible) maxVisible = count;
        }

        System.out.println(maxVisible);
    }
}