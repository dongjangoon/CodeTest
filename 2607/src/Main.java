import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String target = br.readLine();

        // target의 알파벳 개수를 센다.
        int[] targetAlpha = new int[26];
        for (int i = 0; i < target.length(); i++) {
            targetAlpha[target.charAt(i) - 'A']++;
        }

        int result = 0;
        for (int i = 0; i < n-1; i++) {
            String word = br.readLine();

            // words의 알파벳 개수를 센다
            int[] wordsAlpha = new int[26];
            for (int j = 0; j < word.length(); j++) {
                wordsAlpha[word.charAt(j) - 'A']++;
            }

            int lengthDiff = target.length() - word.length();

            // 길이 고려
            if (lengthDiff == 0 && isSimilar(targetAlpha, wordsAlpha) <= 2) {
                result++;
            } else if (lengthDiff == 1 && isSimilar(targetAlpha, wordsAlpha) == 1) {
                result++;
            } else if (lengthDiff == -1 && isSimilar(targetAlpha, wordsAlpha) == 1) {
                result++;
            }
        }

        System.out.println(result);
    }

    private static int isSimilar(int[] targetAlpha, int[] wordsAlpha) {
        int diff = 0;
        for (int i = 0; i < 26; i++) {
            diff += Math.abs(targetAlpha[i] - wordsAlpha[i]);
        }
        return diff;
    }
}