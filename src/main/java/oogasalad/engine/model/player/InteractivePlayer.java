package oogasalad.engine.model.player;

import oogasalad.engine.model.board.Board;

public interface InteractivePlayer {

  void setGameBoard(Board board);

  void onCellSelect(int i, int j);
}
