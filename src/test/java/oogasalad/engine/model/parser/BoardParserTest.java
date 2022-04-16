package oogasalad.engine.model.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.File;
import java.io.FileNotFoundException;
import oogasalad.engine.model.board.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for BoardParser class
 *
 * @author Shaan Gondalia
 */
public class BoardParserTest {

  private static final String TEST_PARSE_FILENAME = "data/tests/parser/test.json";
  private static final int EXPECTED_WIDTH = 8;
  private static final int EXPECTED_HEIGHT = 7;

  private static final int[][] EXPECTED_PIECE_CONFIG = {{-1, -1, -1, -1, -1, -1, -1, -1},
      {-1, -1, 1, -1, -1, -1, -1, -1},
      {-1, 1, -1, -1, 0, -1, -1, -1},
      {-1, -1, -1, 0, -1, -1, -1, -1},
      {-1, 0, 1, -1, -1, -1, -1, -1},
      {-1, -1, 1, -1, -1, -1, -1, -1},
      {-1, -1, -1, -1, -1, -1, -1, -1}};

  private BoardParser parser;

  @BeforeEach
  public void setUp() {
    parser = new BoardParser();
  }

  @Test
  void testParseBoard() throws FileNotFoundException {
    File file = new File(TEST_PARSE_FILENAME);
    Board board = parser.parse(file);
    assertEquals(EXPECTED_WIDTH, board.getWidth());
    assertEquals(EXPECTED_HEIGHT, board.getHeight());
    for (int i = 0; i < EXPECTED_HEIGHT; i++) {
      for (int j = 0; j < EXPECTED_WIDTH; j++) {
        if (EXPECTED_PIECE_CONFIG[i][j] == -1) {
          assertFalse(board.getPieceRecord(i, j).isPresent());
        } else {
          assertEquals(EXPECTED_PIECE_CONFIG[i][j], board.getPieceRecord(i, j).get().type());
        }
      }
    }
  }


}
