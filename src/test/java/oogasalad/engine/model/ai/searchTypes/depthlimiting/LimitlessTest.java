package oogasalad.engine.model.ai.searchTypes.depthlimiting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LimitlessTest {
  private Limitless limitless1;
  private Limitless limitless2;
  private Limitless limitless3;

  @BeforeEach
  void setUp() {
    limitless1 = new Limitless();
    limitless2 = new Limitless();
    limitless3 = new Limitless();
  }

  @Test
  void incrementCurrentDepth() {
    assertDoesNotThrow(() -> limitless1.incrementCurrentDepth());
    assertDoesNotThrow(() -> limitless1.incrementCurrentDepth());
    assertDoesNotThrow(() -> limitless1.incrementCurrentDepth());
    assertDoesNotThrow(() -> limitless1.incrementCurrentDepth());
    assertDoesNotThrow(() -> limitless1.incrementCurrentDepth());
    assertDoesNotThrow(() -> limitless1.incrementCurrentDepth());
    assertDoesNotThrow(() -> limitless1.incrementCurrentDepth());
  }

  @Test
  void getCurrentDepth() {
    // start equal
    assertEquals(limitless1.getCurrentDepth(), limitless2.getCurrentDepth());
    assertEquals(limitless2.getCurrentDepth(), limitless3.getCurrentDepth());

    limitless1.incrementCurrentDepth();
    assertEquals(1, limitless1.getCurrentDepth());
    assertNotEquals(limitless1.getCurrentDepth(), limitless2.getCurrentDepth());
    assertNotEquals(limitless1.getCurrentDepth(), limitless3.getCurrentDepth());

    int incremented = 5;
    for(int i = 0; i < incremented; i++) {
      limitless1.incrementCurrentDepth();
    }

    assertEquals(limitless1.getCurrentDepth(), incremented+1);

    for(int i = 0; i < 10; i++) {
      limitless1.incrementCurrentDepth();
    }


    assertNotEquals(limitless1.getCurrentDepth(), incremented+1);
    assertNotEquals(limitless1.getCurrentDepth(), limitless2.getCurrentDepth());
    assertNotEquals(limitless1.getCurrentDepth(), limitless3.getCurrentDepth());


  }

  @Test
  void shouldContinue() {
    assertTrue(limitless1.shouldContinue());
    for(int i = 0; i < 20; i++) {
      limitless1.incrementCurrentDepth();
      assertTrue(limitless1.shouldContinue());
    }

  }
}