package com.example.Tic_Tac_Toe_Backend.model;

public class GameMessage {
    private String gameId;
    private String player; // "X" or "O"
    private int row;
    private int col;

    // Getters and setters
    public String getGameId() { return gameId; }
    public void setGameId(String gameId) { this.gameId = gameId; }
    public String getPlayer() { return player; }
    public void setPlayer(String player) { this.player = player; }
    public int getRow() { return row; }
    public void setRow(int row) { this.row = row; }
    public int getCol() { return col; }
    public void setCol(int col) { this.col = col; }
}
