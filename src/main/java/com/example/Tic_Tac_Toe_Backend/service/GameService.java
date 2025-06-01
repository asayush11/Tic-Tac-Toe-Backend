package com.example.Tic_Tac_Toe_Backend.service;

import com.example.Tic_Tac_Toe_Backend.model.GameMessage;
import com.example.Tic_Tac_Toe_Backend.model.GameState;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameService {
    private final Map<String, GameState> games = new HashMap<>();

    public GameState createGame(String playerName) {
        String gameId = UUID.randomUUID().toString().substring(0, 8);
        GameState game = new GameState(gameId);
        game.addPlayer(playerName, "X");
        games.put(gameId, game);
        return game;
    }

    public GameState joinGame(String gameId, String playerName) {
        GameState game = games.get(gameId);
        if (game != null && game.getPlayers().size() == 1) {
            game.addPlayer(playerName, "O");
        }
        return game;
    }

    public GameState processMove(GameMessage message) {
        GameState game = games.get(message.gameId());
        if (game == null || game.getWinner() != null || game.isDraw()) return game;

        String[][] board = game.getBoard();
        int row = message.row();
        int col = message.col();

        if ( board[row][col].isEmpty() && message.symbol().equals(game.getCurrentTurn())) {
            board[row][col] = message.symbol();

            if (checkWinner(board, message.symbol())) {
                game.setWinner(message.playerName());
            } else if (checkDraw(board)) {
                game.setDraw(true);
            } else {
                game.setCurrentTurn(message.symbol().equals("X") ? "O" : "X");
            }
        }

        return game;
    }

    public GameState getGame(String gameId) {
        return games.get(gameId);
    }

    private boolean checkWinner(String[][] board, String symbol) {
        for (int i = 0; i < 3; i++) {
            if ((symbol.equals(board[i][0]) && symbol.equals(board[i][1]) && symbol.equals(board[i][2])) ||
                    (symbol.equals(board[0][i]) && symbol.equals(board[1][i]) && symbol.equals(board[2][i]))) {
                return true;
            }
        }
        return (symbol.equals(board[0][0]) && symbol.equals(board[1][1]) && symbol.equals(board[2][2])) ||
                (symbol.equals(board[0][2]) && symbol.equals(board[1][1]) && symbol.equals(board[2][0]));
    }

    private boolean checkDraw(String[][] board) {
        for (String[] row : board) {
            for (String cell : row) {
                if (cell.isEmpty()) return false;
            }
        }
        return true;
    }
}
