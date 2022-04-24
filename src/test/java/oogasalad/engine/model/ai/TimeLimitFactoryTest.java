package oogasalad.engine.model.ai;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import oogasalad.engine.model.ai.timeLimiting.SecondsTimeLimit;
import oogasalad.engine.model.ai.timeLimiting.TimeLimit;
import oogasalad.engine.model.ai.timeLimiting.TimeLimitFactory;
import org.junit.jupiter.api.Test;

class TimeLimitFactoryTest {
  @Test
  void secondsTimeLimitTest() {
    assertInstanceOf(TimeLimit.class, TimeLimitFactory.makeTimeLimit(5));
    assertInstanceOf(SecondsTimeLimit.class, TimeLimitFactory.makeTimeLimit(10));
  }

}