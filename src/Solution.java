class Solution {
    public void solveSudoku(char[][] board) {
        solverUtil(board, 0, 0);
    }

    private boolean solverUtil(char[][] board, int row, int col) {
        if (row > 8) return true;

        int nextCol = (col + 1) % 9;
        int nextRow = col == 8 ? row + 1: row;

        if (board[row][col] != '.') {
            return (solverUtil(board, nextRow, nextCol));
        }

        for (int i = 1; i <= 9; i++) {
            char val = (char) (i + '0');
            if (isValid(val, row, col, board)) {
                board[row][col] = val;

                if (solverUtil(board, nextRow, nextCol)) {
                    return true;
                }

                board[row][col] = '.';
            }
        }
        return false;
    }

    private boolean isValid(char val, int row, int col, char[][] board) {
        int boxRowStart = (row / 3) * 3;
        int boxColStart = (col / 3) * 3;

        for (int k = 0; k < 9; k++) {
            char r = board[row][k];
            char c = board[k][col];
            int rowIdx = boxRowStart + k / 3;
            int colIdx = boxColStart + k % 3;
            char b = board[rowIdx][colIdx];
            if (r == val || c == val || b == val) {
                return false;
            }
        }
        return true;
    }

    public boolean isInitialStateInvalid(char[][] board) {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char v = board[r][c];
                if (v != '.') {
                    board[r][c] = '.';
                    boolean ok = isValid(v, r, c, board);
                    board[r][c] = v;
                    if (!ok) return true;
                }
            }
        }
        return false;
    }
}