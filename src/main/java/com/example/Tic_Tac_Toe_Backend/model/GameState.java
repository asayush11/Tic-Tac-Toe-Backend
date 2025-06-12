package com.example.Tic_Tac_Toe_Backend.model;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class GameState {
    private final String gameId;
    @JsonIgnore
    private final Map<String, String> players; // playerName -> symbol
    private String[][] board = new String[3][3];
    private String currentTurn;
    private String winner;
    private boolean isDraw = false;

    public GameState(String gameID) {
        this.gameId = gameID;
        this.currentTurn = "X";
        this.winner = null;
        this.isDraw = false;
        this.players = new HashMap<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = "";
            }
        }
    }

    // Getters and Setters
    public String getGameId() {
        return gameId;
    }

    public Map<String, String> getPlayers() {
        return players;
    }

    public void addPlayer(String players, String symbol) {
        this.players.put(players, symbol);
    }

    public String[][] getBoard() {
        return board;
    }

    public void setBoard(String[][] board) {
        this.board = board;
    }

    public String getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(String currentTurn) {
        this.currentTurn = currentTurn;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public boolean isDraw() {
        return isDraw;
    }

    public void setDraw(boolean gameOver) {
        isDraw = gameOver;
    }
}
