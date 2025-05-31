package com.example.Tic_Tac_Toe_Backend.service;

import com.example.Tic_Tac_Toe_Backend.model.GameMessage;
import com.example.Tic_Tac_Toe_Backend.model.GameState;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameService {
    private final Map<String, GameState> games = new HashMap<>();

    public GameState processMove(GameMessage message) {
        String gameId = message.getGameId();
        GameState state = games.computeIfAbsent(gameId, id -> new GameState());

        if (!state.isValidMove(message.getPlayer(), message.getRow(), message.getCol())) {
            return state; // invalid move, ignore
        }

        state.makeMove(message.getPlayer(), message.getRow(), message.getCol());
        return state;
    }
}
