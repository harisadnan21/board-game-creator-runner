package oogasalad.engine.model.logicelement.conditions.position_independent_conditions;

import java.util.PrimitiveIterator.OfInt;
import java.util.stream.IntStream;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Piece;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.board.cells.PositionState;
import oogasalad.engine.model.logicelement.conditions.position_independent_conditions.BoardFull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BoardFullTest0 {

  @Test
  void isTrue() {
    Board board = getBoard();
    BoardFull boardFull = new BoardFull(new int[]{0});
    Assertions.assertTrue(boardFull.isTrue(board, null));
  }

  private Board getBoard() {
    Board currentBoard = new Board(4, 4);
    for (OfInt it = IntStream.rangeClosed(0, 3).iterator(); it.hasNext(); ) {
      int i = it.next();
      for (OfInt it1 = IntStream.rangeClosed(0, 3).iterator(); it1.hasNext(); ) {
        int j = it1.next();
        PositionState positionState = new PositionState(new Position(i, j), new Piece(1, 1));
        currentBoard = currentBoard.placePiece(positionState);
      }
    }
    return currentBoard;
  }
}