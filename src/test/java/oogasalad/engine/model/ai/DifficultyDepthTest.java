package oogasalad.engine.model.ai;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DifficultyDepthTest {
  @Test
  void testPresence() {
    assertInstanceOf(int.class, DifficultyDepth.EASY);
    assertInstanceOf(int.class, DifficultyDepth.MEDIUM);
    assertInstanceOf(int.class, DifficultyDepth.HARD);
    assertInstanceOf(int.class, DifficultyDepth.EXPERT);
    assertInstanceOf(int.class, DifficultyDepth.ADAPTIVE);

  }

}