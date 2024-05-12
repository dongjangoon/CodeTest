class Solution {
    public int solution(String s) {
        int answer = 0;
        char x = s.charAt(0);
        int xCount = 1;
        int otherCount = 0;

        for (int i = 1; i < s.length(); i++) {
            char curChar = s.charAt(i);

            if (curChar == x) {
                xCount++;
            } else {
                otherCount++;
            }

            if (xCount == otherCount) {
                if (i < s.length() - 1) {
                    x = s.charAt(i+1);
                }

                xCount = 0;
                otherCount = 0;
                answer++;
            }
        }

        if (xCount > 0 || otherCount > 0) {
            answer++;
        }

        return answer;
    }
}
