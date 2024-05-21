class Solution {
    public int solution(String[] board) {
        int answer = 1;

        // 승, 패 여부 ->
        // 승패가 결정나지 않았다면 o가 x보다 많거나 같아야 함
        int oCount = 0;
        int xCount = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char ch = board[i].charAt(j);
                if (ch == 'O') oCount++;
                if (ch == 'X') xCount++;
            }
        }

        // o, x의 개수가 유효한가
        if (oCount != xCount && oCount != xCount + 1) return 0;

        boolean oWin = isWinner(board, 'O');
        boolean xWin = isWinner(board, 'X');

        // 둘 다 이기면 0
        if (oWin && xWin) return 0;

        // 선공이 이기고 둘 개수가 같으면 0
        if (oWin && (xCount == oCount)) return 0;

        // 후공이 이기고 o가 더 많으면 0
        if (xWin && (oCount > xCount)) return 0;

        // x가 더 많으면 0
        if (xCount > oCount) return 0;

        return answer;
    }

    private boolean isWinner(String[] board, char target) {
        for (int i = 0; i < 3; i++) {
            if (board[i].charAt(0) == target && board[i].charAt(1) == target && board[i].charAt(2) == target) return true;
            if (board[0].charAt(i) == target && board[1].charAt(i) == target && board[2].charAt(i) == target) return true;
        }
        if (board[0].charAt(0) == target && board[1].charAt(1) == target && board[2].charAt(2) == target) return true;
        if (board[0].charAt(2) == target && board[1].charAt(1) == target && board[2].charAt(0) == target) return true;

        return false;
    }
}
