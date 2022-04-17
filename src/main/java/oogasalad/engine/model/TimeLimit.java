package oogasalad.engine.model;

public interface TimeLimit {

  // Starts time limit
  void start();

  // returns true if time limit reached, else false
  boolean isTimeUp();

}
