package com.example.Tic_Tac_Toe_Backend.controller;

import com.example.Tic_Tac_Toe_Backend.model.GameMessage;
import com.example.Tic_Tac_Toe_Backend.model.GameState;
import com.example.Tic_Tac_Toe_Backend.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GameController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private GameService gameService;

    @MessageMapping("/play")
    public void handleMove(@Payload GameMessage message) {
        var state = gameService.processMove(message);
        messagingTemplate.convertAndSend("/topic/game/" + message.gameId(), state);
    }

    @PostMapping("/game/create")
    public ResponseEntity<GameState> createGame(@RequestParam String playerName) {
        GameState gameState = gameService.createGame(playerName);
        if(gameState != null){
            return ResponseEntity.ok(gameState);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(gameState);
        }
    }

    @PostMapping("/game/join")
    public ResponseEntity<GameState> joinGame(@RequestParam String gameId, @RequestParam String playerName) {
        GameState gameState = gameService.joinGame(gameId, playerName);
        if(gameState != null){
            return ResponseEntity.ok(gameState);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(gameState);
        }
    }

    @GetMapping("/game/state/{gameId}")
    public ResponseEntity<GameState> getGame(@PathVariable String gameId) {
        GameState game = gameService.getGame(gameId);
        if(game != null) return ResponseEntity.ok(game);
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(game);
    }
}
