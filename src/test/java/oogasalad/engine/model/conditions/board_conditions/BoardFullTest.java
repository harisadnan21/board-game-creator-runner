package oogasalad.engine.model.conditions.board_conditions;

import java.util.PrimitiveIterator.OfInt;
import java.util.stream.IntStream;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Piece;
import oogasalad.engine.model.board.Position;
import oogasalad.engine.model.board.PositionState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BoardFullTest {

  @Test
  void isTrue() throws CloneNotSupportedException {
    Board board = getBoard();
    BoardFull boardFull = new BoardFull();
    Assertions.assertTrue(boardFull.isTrue(board));
  }

  private Board getBoard() throws CloneNotSupportedException {
    Board currentBoard = new Board(4, 4);
    for (OfInt it = IntStream.rangeClosed(1, 4).iterator(); it.hasNext(); ) {
      int x = it.next();
      for (OfInt it1 = IntStream.rangeClosed(1, 4).iterator(); it1.hasNext(); ) {
        int y = it1.next();
        PositionState positionState = new PositionState(new Position(x, y), Piece.EMPTY);
        currentBoard = currentBoard.placePiece(positionState);
      }
    }
    return currentBoard;
  }
}