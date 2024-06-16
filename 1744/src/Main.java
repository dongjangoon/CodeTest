import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Integer> positives = new ArrayList<>();
        List<Integer> negatives = new ArrayList<>();
        int zeroCount = 0;
        int oneCount = 0;

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num == 0) {
                zeroCount++;
            } else if (num == 1) {
                oneCount++;
            } else if (num < 0) {
                negatives.add(num);
            } else {
                positives.add(num);
            }
        }

        // 양수는 큰 순서대로 정렬, 음수는 작은 순서대로
        Collections.sort(positives, Collections.reverseOrder());
        Collections.sort(negatives);

        // 1은 그대로 더하기
        int result = oneCount;

        for (int i = 0; i < positives.size(); i += 2) {
            if (i + 1 < positives.size()) {
                result += positives.get(i) * positives.get(i + 1);
            } else {
                result += positives.get(i);
            }
        }

        for (int i = 0; i < negatives.size(); i += 2) {
            if (i + 1 < negatives.size()) {
                result += negatives.get(i) * negatives.get(i + 1);
            } else {
                if (zeroCount > 0) {
                    zeroCount--;
                } else {
                    result += negatives.get(i);
                }
            }
        }

        System.out.println(result);
    }
}