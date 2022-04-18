package oogasalad.engine.model.ai;

import io.vavr.collection.TreeSet;
import java.util.Collection;
import oogasalad.engine.model.ai.evaluation.StateEvaluator;
import oogasalad.engine.model.ai.evaluation.meta.CenterAround;
import oogasalad.engine.model.ai.evaluation.meta.SeekEquality;
import oogasalad.engine.model.ai.evaluation.patterns.DiffBasedPatternProximityEvaluator;
import oogasalad.engine.model.ai.evaluation.patterns.Pattern;
import oogasalad.engine.model.ai.evaluation.totals.TotalPieces;
import oogasalad.engine.model.ai.searchTypes.AlphaBetaSearcher;
import oogasalad.engine.model.ai.searchTypes.MinMaxSearcher;
import oogasalad.engine.model.ai.searchTypes.RandomSearcher;
import oogasalad.engine.model.ai.searchTypes.Selects;
import oogasalad.engine.model.board.Piece;

// TODO: clean this up
public class SelectorFactory {

  public static Selects makeSelector(Difficulty difficulty, WinType winType, int playerNumber, AIOracle aiOracle, Collection<Pattern> patterns) {
    return difficulty.equals(Difficulty.RANDOM)? makeRandomSelector(aiOracle, playerNumber) : makeNonRandomSelector(difficulty, winType, playerNumber, aiOracle, patterns);
  }

  private static Selects makeNonRandomSelector(Difficulty difficulty, WinType winType, int playerNumber, AIOracle aiOracle, Collection<Pattern> patterns) {
    StateEvaluator evaluator = getEvaluator(winType, patterns);
    return switch (difficulty) {
      case EASY -> getEasyEvaluator(evaluator, playerNumber, aiOracle);
      case MEDIUM -> getMediumEvaluator(evaluator, playerNumber, aiOracle);
      case HARD -> getHardEvaluator(evaluator, playerNumber, aiOracle);
      case EXPERT -> getExpertEvaluator(evaluator, playerNumber, aiOracle);
      case ADAPTIVE -> getAdaptiveEvaluator(evaluator, playerNumber, aiOracle);
      default -> throw new IllegalStateException("Unexpected value: " + difficulty);
    };
  }

  private static Selects getAdaptiveEvaluator(StateEvaluator evaluator, int playerNumber, AIOracle aiOracle) {
    boolean isPlayerOne = playerNumber== Piece.PLAYER_ONE;
    StateEvaluator adaptiveEvaluator = new SeekEquality(evaluator, isPlayerOne, !isPlayerOne);
    return new MinMaxSearcher(DifficultyDepth.ADAPTIVE, playerNumber, adaptiveEvaluator, aiOracle);
  }

  private static Selects getExpertEvaluator(StateEvaluator evaluator, int playerNumber, AIOracle aiOracle) {
    return new AlphaBetaSearcher(DifficultyDepth.EXPERT, playerNumber, evaluator, aiOracle);
  }

  private static Selects getHardEvaluator(StateEvaluator evaluator, int playerNumber, AIOracle aiOracle) {
    return new MinMaxSearcher(DifficultyDepth.HARD, playerNumber, evaluator, aiOracle);
  }

  private static Selects getMediumEvaluator(StateEvaluator evaluator, int playerNumber, AIOracle aiOracle) {
    return new MinMaxSearcher(DifficultyDepth.MEDIUM, playerNumber, evaluator, aiOracle);
  }

  private static Selects getEasyEvaluator(StateEvaluator evaluator, int playerNumber, AIOracle aiOracle) {
    return new MinMaxSearcher(DifficultyDepth.EASY, playerNumber, evaluator, aiOracle);
  }

  private static StateEvaluator getEvaluator(WinType winType, Collection<Pattern> patterns) {
    return winType.equals(WinType.TOTAL)? new TotalPieces(): new DiffBasedPatternProximityEvaluator(TreeSet.ofAll(patterns));

  }

  private static Selects makeRandomSelector(AIOracle aiOracle, int player) {
    return new RandomSearcher(aiOracle, player);
  }

}