package oogasalad.engine.model.ai.timeLimiting;

/**
 * @author Alex Bildner
 */
public interface TimeLimit {

  void start();


  boolean isTimeUp();

}
