package oogasalad.engine.model.conditionals;

import oogasalad.engine.model.board.Position;

@FunctionalInterface
public interface PositionsTrait {
    public Boolean positionsTrait(Position[] positions);
}

