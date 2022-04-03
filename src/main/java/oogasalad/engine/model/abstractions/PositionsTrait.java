package oogasalad.engine.model.abstractions;

import oogasalad.engine.model.board.Position;

@FunctionalInterface
public interface PositionsTrait {
    public Boolean positionsTrait(Position[] positions);
}

