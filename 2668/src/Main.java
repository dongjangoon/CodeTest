import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static void dfs(List<Integer> answer, int[] secondLine, boolean[] check, int i, int num) {
        // 다시 제자리로 오면 정답에 추가
        if (secondLine[i] == num) {
            answer.add(num);
        }
        if (!check[secondLine[i]]) {
            check[secondLine[i]] = true;
            dfs(answer, secondLine, check, secondLine[i], num);
            check[secondLine[i]] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] secondLine = new int[n+1];
        boolean[] check = new boolean[n+1];

        for (int i = 1; i <= n; i++) {
            secondLine[i] = Integer.parseInt(br.readLine());
        }

        List<Integer> answer = new ArrayList<>();

        int num;
        for (int i = 1; i <= n; i++) {
            check[i] = true;
            num = i;
            dfs(answer, secondLine, check, i, num);
            check[i] = false;
        }

        Collections.sort(answer);

        System.out.println(answer.size());
        for (int i: answer) {
            System.out.println(i);
        }
    }
}