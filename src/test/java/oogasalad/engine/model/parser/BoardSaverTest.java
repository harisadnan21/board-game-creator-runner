package oogasalad.engine.model.parser;

import java.io.File;
import java.io.IOException;
import oogasalad.engine.model.board.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoardSaverTest {

  private static final String TEST_PARSE_FILENAME = "data/tests/parser/test.json";
  private static final String TEST_PARSE_DIRECTORY = "data/tests/parser/";
  private static final String TEST_SAVE_DIRECTORY = "data/tests/parser/saver/";

  private BoardSaver saver;
  private BoardParser parser;

  @BeforeEach
  public void setUp() {
    saver = new BoardSaver(new File(TEST_PARSE_DIRECTORY));
    parser = new BoardParser();
  }

  @Test
  void testSaveModifiedBoard() throws IOException {
    File file = new File(TEST_PARSE_FILENAME);
    Board board = parser.parse(file);
    Board newBoard = board.placeNewPiece(0,0,2,1);
    saver.saveBoardConfig(newBoard, new File(TEST_SAVE_DIRECTORY));
  }

}
