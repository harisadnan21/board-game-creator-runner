package oogasalad.engine.model.logicelement.conditions.position_independent_conditions;

import java.util.Random;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;

/**
 * Returns true with probability 1/n
 *
 * Generates a single random number at the beginning if constant is true
 *
 * @author Jake Heller
 */
public class RandomChance extends BoardCondition {

  private int n;
  private boolean constant;
  private boolean invert;

  private boolean result;
  /**
   *
   * @param parameters size 2 array [n, constant, invert]
   */
  public RandomChance(int[] parameters) {
    super(parameters);
    n = getParameter(0);
    constant = getParameter(1) != 0;
    invert = getParameter(2) != 0;
    result = generateResult(n);
  }

  @Override
  public boolean isTrue(Board board, Position referencePoint) {
    boolean currentResult = constant ? result : generateResult(n);
    return invertIfTrue(currentResult, invert);
  }

  private boolean generateResult(int n) {
    Random rand = new Random();
    int roll = rand.nextInt(n);
    return roll == 0;
  }
}
