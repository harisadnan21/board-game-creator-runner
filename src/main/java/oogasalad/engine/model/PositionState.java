package oogasalad.engine.model;


import oogasalad.engine.model.Player;
import oogasalad.engine.model.board.Position;

public record PositionState(Position position, String player, String pieceType) {}
