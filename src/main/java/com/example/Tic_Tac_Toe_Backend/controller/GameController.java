package com.example.Tic_Tac_Toe_Backend.controller;

import com.example.Tic_Tac_Toe_Backend.model.GameMessage;
import com.example.Tic_Tac_Toe_Backend.model.GameState;
import com.example.Tic_Tac_Toe_Backend.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return ResponseEntity.ok(gameService.createGame(playerName));
    }

    @PostMapping("/game/join")
    public ResponseEntity<GameState> joinGame(@RequestParam String gameId, @RequestParam String playerName) {
        return ResponseEntity.ok(gameService.joinGame(gameId, playerName));
    }

    @GetMapping("/game/state/{gameId}")
    public ResponseEntity<GameState> getGame(@PathVariable String gameId) {
        return ResponseEntity.ok(gameService.getGame(gameId));
    }
}
