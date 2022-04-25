package oogasalad.engine.cheat_codes;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.function.Consumer;
import oogasalad.engine.controller.Controller;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.parser.GameParser;
import oogasalad.engine.view.game.BoardView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IncrementPlayerTest {
  Board myBoard = new Board(3,3);
  Controller controller = new Controller(new String[]{"human", "human"});
  BoardView board;
  Board backEndBoard;

  @BeforeEach
  void setup() throws IOException {
    GameParser parser = new GameParser(new File("data/games/checkers/config.json"));

    backEndBoard =  parser.parseBoard();
    controller = new Controller(new String[]{"human", "human"});

    controller.startEngine(parser, this::fakeConsumer, this::fakeConsumer1);
    myBoard.placeNewPiece(1, 1, 0, 0);
    myBoard.placeNewPiece(2, 2, 0, 1);
  }
  private void fakeConsumer(Set<Position> test){}
  private void fakeConsumer1(int test){}

  @Test
  void testIncrementPlayerFromZeroToOneTest(){
    IncrementPlayer inc = new IncrementPlayer();
    Board newBoard = inc.accept(backEndBoard, controller);
    assertEquals(newBoard.getPlayer(), backEndBoard.getPlayer()+1);
  }

  @Test
  void testIncrementPlayerFromOneToZeroTest(){
    IncrementPlayer inc = new IncrementPlayer();
    Board newBoard = inc.accept(backEndBoard, controller);
    newBoard = inc.accept(newBoard, controller);
    assertEquals(newBoard.getPlayer(), backEndBoard.getPlayer());
  }


}