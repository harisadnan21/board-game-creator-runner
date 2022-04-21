package oogasalad.engine.model.ai;

public class TimeLimitFactory {
  public static TimeLimit makeTimeLimit (int seconds) {
    return new SecondsTimeLimit(seconds);
  }

}
