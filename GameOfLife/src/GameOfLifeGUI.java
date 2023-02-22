import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class GameOfLifeGUI extends JFrame implements ActionListener {
    private final int ROWS = 30;
    private final int COLS = 30;
    private final int CELL_SIZE = 10;
    private boolean[][] grid = new boolean[ROWS][COLS];
    private boolean started = false;
    private JButton startButton;
    private JPanel gridPanel;
    private JButton restartButton;
    private JLabel countLabel = new JLabel("<html>Cells updates:<br>0</html>");
    public GameOfLifeGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Game of Life");
        setResizable(false);
        add(countLabel, BorderLayout.EAST);

        JOptionPane.showMessageDialog(null, "Welcome to Game Of Life\n\nThe Game of Life is a two-dimensional automaton where each cell in the grid can be either \"alive\" or \"dead\". The behavior of the automaton is determined by simple rules:\n\n1. Any live cell with fewer than two live neighbors dies, as if by underpopulation.\n2. Any live cell with two or three live neighbors lives on to the next generation.\n3. Any live cell with more than three live neighbors dies, as if by overpopulation.\n4. Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.", "Game of Life Rules", JOptionPane.INFORMATION_MESSAGE);

        restartButton = new JButton("Restart");
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < ROWS; i++) {
                    for (int j = 0; j < COLS; j++) {
                        grid[i][j] = false;
                        JPanel cellPanel = (JPanel) gridPanel.getComponent(i * COLS + j);
                        cellPanel.setBackground(Color.GRAY);
                    }
                }
                started = false;
                startButton.setText("Start");
            }
        });
        add(restartButton, BorderLayout.NORTH);

        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(ROWS, COLS));
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                JPanel cellPanel = new JPanel();
                cellPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                cellPanel.setBackground(Color.GRAY);
                cellPanel.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
                cellPanel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (!started) {
                            int row = (int) ((JPanel) e.getSource()).getClientProperty("row");
                            int col = (int) ((JPanel) e.getSource()).getClientProperty("col");
                            if (!grid[row][col]) {
                                grid[row][col] = true;
                                cellPanel.setBackground(Color.YELLOW);
                            } else {
                                grid[row][col] = false;
                                cellPanel.setBackground(Color.GRAY);
                            }
                        }
                    }
                });
                cellPanel.putClientProperty("row", i);
                cellPanel.putClientProperty("col", j);
                gridPanel.add(cellPanel);
            }
        }
        add(gridPanel, BorderLayout.CENTER);

        startButton = new JButton("Start");
        startButton.addActionListener(this);
        add(startButton, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!started) {
            started = true;
            startButton.setText("Stop");
            new Thread(() -> runSimulation()).start();
        } else {
            started = false;
            startButton.setText("Start");
        }
    }
    private int getLivingNeighbors(int row, int col) {
        int count = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < ROWS && j >= 0 && j < COLS && !(i == row && j == col) && grid[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }


    private void runSimulation() {
        int count = 0; // initialize count to 0
        int prevCount = 0;
        while (started) {
            boolean[][] newGrid = new boolean[ROWS][COLS];
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                    int livingNeighbors = getLivingNeighbors(i, j);
                    if (grid[i][j]) {
                        if (livingNeighbors == 2 || livingNeighbors == 3) {
                            newGrid[i][j] = true;
                        }
                    } else {
                        if (livingNeighbors == 3) {
                            newGrid[i][j] = true;
                        }
                    }
                    if (grid[i][j] != newGrid[i][j]) {
                        count++; // increment count for each updated cell
                    }
                }
            }
            countLabel.setText("<html>Cells updates: <br>" + count + "</html>");
            if (count == prevCount) {
                resetGame();
                break;
            }
            prevCount = count;
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                    if (grid[i][j] != newGrid[i][j]) {
                        grid[i][j] = newGrid[i][j];
                        JPanel cellPanel = (JPanel) gridPanel.getComponent(i * COLS + j);
                        if (newGrid[i][j]) {
                            cellPanel.setBackground(Color.YELLOW);
                        } else {
                            cellPanel.setBackground(Color.GRAY);
                        }
                    }
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void resetGame() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                grid[i][j] = false;
                JPanel cellPanel = (JPanel) gridPanel.getComponent(i * COLS + j);
                cellPanel.setBackground(Color.GRAY);
            }
        }
        started = false;
        startButton.setText("Start");
        countLabel.setText("<html>Cells updates: <br>0</html>");
    }

    public static void main(String[] args) {
        new GameOfLifeGUI();
    }
}