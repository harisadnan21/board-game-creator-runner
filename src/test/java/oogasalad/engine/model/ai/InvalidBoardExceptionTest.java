package oogasalad.engine.model.ai;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class InvalidBoardExceptionTest {

  @Test
  void testInvalidBoardExceptionTest() {
    assertInstanceOf(RuntimeException.class, new InvalidBoardException());
    assertInstanceOf(InvalidBoardException.class, new InvalidBoardException());
  }

}