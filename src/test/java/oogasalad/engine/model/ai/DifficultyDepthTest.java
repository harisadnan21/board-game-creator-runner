package oogasalad.engine.model.ai;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DifficultyDepthTest {
  @Test
  void testPresence() {
    assertInstanceOf(Integer.class, DifficultyDepth.EASY);
    assertInstanceOf(Integer.class, DifficultyDepth.MEDIUM);
    assertInstanceOf(Integer.class, DifficultyDepth.HARD);
    assertInstanceOf(Integer.class, DifficultyDepth.EXPERT);
    assertInstanceOf(Integer.class, DifficultyDepth.ADAPTIVE);

  }

}