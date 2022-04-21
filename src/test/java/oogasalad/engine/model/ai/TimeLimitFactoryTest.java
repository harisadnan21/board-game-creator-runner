package oogasalad.engine.model.ai;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TimeLimitFactoryTest {
  @Test
  void secondsTimeLimitTest() {
    assertInstanceOf(TimeLimit.class, TimeLimitFactory.makeTimeLimit(5));
    assertInstanceOf(SecondsTimeLimit.class, TimeLimitFactory.makeTimeLimit(10));
  }

}