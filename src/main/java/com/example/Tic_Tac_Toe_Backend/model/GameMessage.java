package com.example.Tic_Tac_Toe_Backend.model;

/**
 * @param messageType JOIN, MOVE, UPDATE, END
 */
public record GameMessage(String gameId, String playerName, String symbol, int row, int col, String messageType) {

}
