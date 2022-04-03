package oogasalad.engine.model.board;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardTest {
  private Board myBoard;

  @BeforeEach
  void setUp() {
    int rows = 3;
    int columns = 3;
    myBoard = new Board(3, 3);
  }

  @Test
  void testTwoDoubleObjects(){


  }
}