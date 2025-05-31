package com.example.Tic_Tac_Toe_Backend.controller;

import com.example.Tic_Tac_Toe_Backend.model.GameMessage;
import com.example.Tic_Tac_Toe_Backend.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/play")
    public void handleMove(@Payload GameMessage message) {
        var state = gameService.processMove(message);
        messagingTemplate.convertAndSend("/topic/game/" + message.getGameId(), state);
    }
}
