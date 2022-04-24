package oogasalad.engine.model.ai.evaluation;

/**
 * @author Alex Bildner
 */
public record Evaluation (int playerOneEvaluation, int playerTwoEvaluation) {

  public int forPlayer(int player) {
    return player == 1 ? playerOneEvaluation : playerTwoEvaluation;
  }

}
