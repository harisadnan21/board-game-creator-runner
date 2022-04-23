package oogasalad.engine.model.engine;

public class Counter {
  private int counter;

  public Counter() {
    counter = 0;
  }

  public synchronized void increment() {
    counter++;
  }

  public synchronized int getCount() {
    return counter;
  }
}
