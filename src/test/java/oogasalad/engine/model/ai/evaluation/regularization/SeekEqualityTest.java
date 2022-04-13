package oogasalad.engine.model.ai.evaluation.regularization;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SeekEqualityTest {
  private static SeekEquality seekEquality = new SeekEquality();
  private static CenterAround centerAroundWithZero = new CenterAround(0);
  private static CenterAround centerAroundWithFive = new CenterAround(5);
  private static CenterAround centerAroundWithTen = new CenterAround(10);

  @Test
  void regularizeEvaluationScore() {
    //TODO: implement check that there is proper equality and inequality for static fields evaluations
  }
}