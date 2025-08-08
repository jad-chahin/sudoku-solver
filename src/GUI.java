import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    private final JTextField[][] cells = new JTextField[9][9];
    private final Solution solver = new Solution();

    public GUI() {
        super("Sudoku Solver");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        /* --- 9×9 grid --- */
        JPanel grid = new JPanel(new GridLayout(9, 9, 2, 2));   // gaps = 2 px
        Font big = new Font(Font.MONOSPACED, Font.BOLD, 22);
        Dimension pref = new Dimension(45, 45);
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                JTextField tf = new JTextField();
                tf.setHorizontalAlignment(JTextField.CENTER);
                tf.setFont(big);
                tf.setPreferredSize(pref);
                cells[r][c] = tf;
                grid.add(tf);
            }
        }

        JButton solveBtn = new JButton("Solve");
        JButton clearBtn = new JButton("Clear");
        solveBtn.addActionListener(_ -> solve());
        clearBtn.addActionListener(_ -> clear());

        JPanel controls = new JPanel();
        controls.add(solveBtn);
        controls.add(clearBtn);

        add(grid, BorderLayout.CENTER);
        add(controls, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void solve() {
        char[][] board = new char[9][9];
        for (int r = 0; r < 9; r++)
            for (int c = 0; c < 9; c++) {
                String t = cells[r][c].getText().trim();
                board[r][c] = t.isEmpty() ? '.' : t.charAt(0);
            }

        if (solver.isInitialStateInvalid(board)) {
            JOptionPane.showMessageDialog(this, "The puzzle already violates Sudoku rules.",
                    "Invalid puzzle", JOptionPane.WARNING_MESSAGE);
            return;
        }

        solver.solveSudoku(board);

        for (int r = 0; r < 9; r++)
            for (int c = 0; c < 9; c++)
                cells[r][c].setText(board[r][c] == '.' ? "" : String.valueOf(board[r][c]));
    }

    private void clear() {
        for (JTextField[] row : cells)
            for (JTextField tf : row) tf.setText("");
    }
}