package oogasalad.engine.model.ai.timeLimiting;

public class TimeLimitFactory {
  public static TimeLimit makeTimeLimit (int seconds) {
    return new SecondsTimeLimit(seconds);
  }

}
