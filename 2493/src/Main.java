import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] tower = new int[n+1];

        for (int i = 1; i <= n; i++) {
            tower[i] = Integer.parseInt(st.nextToken());
        }

        Stack<Integer> stack = new Stack<>();
        int[] answer = new int[n];

        for (int i = 1; i <= n; i++) {
            while (!stack.isEmpty() && tower[stack.peek()] < tower[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                answer[i-1] = stack.peek();
            } else {
                answer[i-1] = 0;
            }
            stack.push(i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(answer[i]).append(" ");
        }
        System.out.println(sb.toString().trim());
    }
}
