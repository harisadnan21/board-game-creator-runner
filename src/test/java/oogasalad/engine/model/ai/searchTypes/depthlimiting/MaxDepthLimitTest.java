package oogasalad.engine.model.ai.searchTypes.depthlimiting;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.jooq.lambda.Seq;
import org.jooq.lambda.tuple.Range;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MaxDepthLimitTest {

    private MaxDepthLimit maxDepthLimit1;
    private MaxDepthLimit maxDepthLimit5;
    private MaxDepthLimit maxDepthLimit10;

    @BeforeEach
    void setUp() {
      maxDepthLimit1 = new MaxDepthLimit(1);
      maxDepthLimit5 = new MaxDepthLimit(5);
      maxDepthLimit10 = new MaxDepthLimit(10);
    }

    @Test
    void incrementCurrentDepth() {
      assertDoesNotThrow(() -> maxDepthLimit1.incrementCurrentDepth());
      assertDoesNotThrow(() -> maxDepthLimit1.incrementCurrentDepth());

      assertDoesNotThrow(() -> maxDepthLimit5.incrementCurrentDepth());
      assertDoesNotThrow(() -> maxDepthLimit10.incrementCurrentDepth());

    }

    @Test
    void getCurrentDepth() {
      // start equal
      assertEquals(maxDepthLimit1.getCurrentDepth(), maxDepthLimit5.getCurrentDepth());
      assertEquals(maxDepthLimit5.getCurrentDepth(), maxDepthLimit10.getCurrentDepth());
      assertEquals(maxDepthLimit1.getCurrentDepth(), maxDepthLimit10.getCurrentDepth());

      maxDepthLimit1.incrementCurrentDepth();
      assertEquals(1, maxDepthLimit1.getCurrentDepth());
      assertNotEquals(maxDepthLimit1.getCurrentDepth(), maxDepthLimit5.getCurrentDepth());
      assertNotEquals(maxDepthLimit1.getCurrentDepth(), maxDepthLimit10.getCurrentDepth());

      int incremented = 5;
      for(int i = 0; i < incremented; i++) {
        maxDepthLimit1.incrementCurrentDepth();
      }

      assertEquals(maxDepthLimit1.getCurrentDepth(), incremented+1);

      for(int i = 0; i < 10; i++) {
        maxDepthLimit1.incrementCurrentDepth();
      }


      assertNotEquals(maxDepthLimit1.getCurrentDepth(), incremented+1);
      assertNotEquals(maxDepthLimit1.getCurrentDepth(), maxDepthLimit5.getCurrentDepth());
      assertNotEquals(maxDepthLimit1.getCurrentDepth(), maxDepthLimit10.getCurrentDepth());


    }

    @Test
    void shouldContinue() {
      assertTrue(maxDepthLimit1.shouldContinue());
      assertTrue(maxDepthLimit5.shouldContinue());
      assertTrue(maxDepthLimit10.shouldContinue());

      maxDepthLimit1.incrementCurrentDepth();
      maxDepthLimit5.incrementCurrentDepth();
      maxDepthLimit10.incrementCurrentDepth();

      assertFalse(maxDepthLimit1.shouldContinue());
      assertTrue(maxDepthLimit5.shouldContinue());
      assertTrue(maxDepthLimit10.shouldContinue());

      Seq.range(0,4).forEach(integer ->
          {maxDepthLimit1.incrementCurrentDepth(); maxDepthLimit5.incrementCurrentDepth(); maxDepthLimit10.incrementCurrentDepth();}
        );

      assertFalse(maxDepthLimit1.shouldContinue());
      assertFalse(maxDepthLimit5.shouldContinue());
      assertTrue(maxDepthLimit10.shouldContinue());

      Seq.range(0,5).forEach(integer ->
          {maxDepthLimit1.incrementCurrentDepth(); maxDepthLimit5.incrementCurrentDepth(); maxDepthLimit10.incrementCurrentDepth();}
      );

      assertFalse(maxDepthLimit1.shouldContinue());
      assertFalse(maxDepthLimit5.shouldContinue());
      assertFalse(maxDepthLimit10.shouldContinue());

      Seq.range(0,6).forEach(integer ->
          {maxDepthLimit1.incrementCurrentDepth(); maxDepthLimit5.incrementCurrentDepth(); maxDepthLimit10.incrementCurrentDepth();}
      );

      assertFalse(maxDepthLimit1.shouldContinue());
      assertFalse(maxDepthLimit5.shouldContinue());
      assertFalse(maxDepthLimit10.shouldContinue());
    }

}
