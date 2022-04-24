package oogasalad.engine.model.ai.timeLimiting;

/**
 * @author Alex Bildner
 */
public class TimeLimitFactory {
  public static TimeLimit makeTimeLimit (int seconds) {
    return new SecondsTimeLimit(seconds);
  }

}
