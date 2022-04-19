package oogasalad.engine.model.ai;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DifficultyDepthConstantsTest {
  @Test
  void testPresence() {
    assertInstanceOf(Integer.class, DifficultyDepthConstants.EASY);
    assertInstanceOf(Integer.class, DifficultyDepthConstants.MEDIUM);
    assertInstanceOf(Integer.class, DifficultyDepthConstants.HARD);
    assertInstanceOf(Integer.class, DifficultyDepthConstants.EXPERT);
    assertInstanceOf(Integer.class, DifficultyDepthConstants.ADAPTIVE);

  }

}