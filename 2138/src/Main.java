import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        String current = sc.next();
        String target = sc.next();

        int[] currentState = new int[n]; // 1번 스위치를 안 누른 경우
        int[] targetState = new int[n];

        for (int i = 0; i < n; i++) {
            currentState[i] = current.charAt(i) - '0';
            targetState[i] = target.charAt(i) - '0';
        }

        int[] copyOfCurrentState = Arrays.copyOf(currentState, n); // 1번 스위치를 누른 경우
        copyOfCurrentState[0] = 1 - currentState[0];
        copyOfCurrentState[1] = 1 - currentState[1];

        int result1 = solve(n, currentState, targetState); // 1번 스위치 안 누른 경우
        int result2 = solve(n, copyOfCurrentState, targetState); // 1번 스위치를 누른 경우
        if (result2 != -1) result2++;

        if (result1 == -1) {
            System.out.println(result2);
        } else if (result2 == -1) {
            System.out.println(result1);
        } else {
            System.out.println(Math.min(result1, result2));
        }
    }

    private static int solve(int n, int[] currentState, int[] targetState) {
        int result = 0;
        for (int i = 0; i < n-1; i++) {
            if (currentState[i] != targetState[i]) {
                result++;
                currentState[i] ^= 1;
                currentState[i + 1] ^= 1;
                if (i != n - 2) currentState[i + 2] ^= 1;
            }
        }
        return currentState[n-1] != targetState[n-1] ? -1 : result;
    }
}