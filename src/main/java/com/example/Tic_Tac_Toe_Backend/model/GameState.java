package com.example.Tic_Tac_Toe_Backend.model;

public class GameState {
    private String[][] board = new String[3][3];
    private String currentTurn = "X";
    private String winner = null;
    private boolean draw = false;

    public GameState() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = "";
    }

    public boolean isValidMove(String player, int row, int col) {
        return winner == null &&
                row >= 0 && row < 3 && col >= 0 && col < 3 &&
                board[row][col].isEmpty() &&
                player.equals(currentTurn);
    }

    public void makeMove(String player, int row, int col) {
        board[row][col] = player;
        checkWinner();
        if (winner == null && isFull()) draw = true;
        currentTurn = player.equals("X") ? "O" : "X";
    }

    private void checkWinner() {
        String[][] b = board;
        String[] lines = {
                b[0][0] + b[0][1] + b[0][2],
                b[1][0] + b[1][1] + b[1][2],
                b[2][0] + b[2][1] + b[2][2],
                b[0][0] + b[1][0] + b[2][0],
                b[0][1] + b[1][1] + b[2][1],
                b[0][2] + b[1][2] + b[2][2],
                b[0][0] + b[1][1] + b[2][2],
                b[0][2] + b[1][1] + b[2][0],
        };
        for (String line : lines) {
            if (line.equals("XXX")) winner = "X";
            else if (line.equals("OOO")) winner = "O";
        }
    }

    private boolean isFull() {
        for (String[] row : board)
            for (String cell : row)
                if (cell.isEmpty()) return false;
        return true;
    }

    // Getters
    public String[][] getBoard() { return board; }
    public String getCurrentTurn() { return currentTurn; }
    public String getWinner() { return winner; }
    public boolean isDraw() { return draw; }
}
