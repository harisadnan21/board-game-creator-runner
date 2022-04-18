package oogasalad.builder.model.board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for RectangularBoard
 *
 * @author Shaan Gondalia
 */
public class RectangularBoardTest {

  private static final int HEIGHT = 8;
  private static final int WIDTH = 10;
  private static final int PIECE_ID = 100;
  private static final int EMPTY = -1;
  private static final int X = 5;
  private static final int Y = 7;
  private Board board;

  @BeforeEach
  void setUp(){
      board = new RectangularBoard(WIDTH, HEIGHT);
  }

  @Test
  void testOutOfBounds(){
    assertThrows(IndexOutOfBoundsException.class, () -> board.placePiece(HEIGHT+1, WIDTH + 1, PIECE_ID));
    assertThrows(IndexOutOfBoundsException.class, () -> board.clearCell(HEIGHT+1, WIDTH + 1));
    assertThrows(IndexOutOfBoundsException.class, () -> board.findPieceAt(HEIGHT+1, WIDTH + 1));
    assertThrows(IndexOutOfBoundsException.class, () -> board.placePiece(-1, WIDTH + 1, PIECE_ID));
    assertThrows(IndexOutOfBoundsException.class, () -> board.clearCell(-1, WIDTH + 1));
    assertThrows(IndexOutOfBoundsException.class, () -> board.findPieceAt(-1, WIDTH + 1));
  }

  @Test
  void testPiecePlacement() {
    board.placePiece(X, Y, PIECE_ID);
    assertEquals(PIECE_ID, board.findPieceAt(X, Y));
  }

  @Test
  void testEmpty() {
    for (int i = 0; i < WIDTH; i++) {
      for (int j = 0; j < HEIGHT; j++) {
        assertEquals(EMPTY, board.findPieceAt(i, j));
      }
    }
    board.placePiece(X, Y, PIECE_ID);
    board.clearCell(X, Y);
    assertEquals(EMPTY, board.findPieceAt(X, Y));
  }

  @Test
  void testSerialization() {
    String json = board.toJSON();
    assertEquals(WIDTH * HEIGHT, countMatches(json, Integer.toString(EMPTY)));

    board.placePiece(X, Y, PIECE_ID);
    json = board.toJSON();
    assertEquals(WIDTH * HEIGHT - 1, countMatches(json, Integer.toString(EMPTY)));
    assertEquals(1, countMatches(json, Integer.toString(PIECE_ID)));
  }

  private int countMatches(String str, String target) {
    int lastIndex = 0;
    int count = 0;

    while (lastIndex != -1) {
      lastIndex = str.indexOf(target, lastIndex);
      if (lastIndex != -1) {
        count++;
        lastIndex += target.length();
      }
    }
    return count;
  }

}
