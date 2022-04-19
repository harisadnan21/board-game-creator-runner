package oogasalad.engine.model.ai;

import io.vavr.collection.TreeSet;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import oogasalad.engine.model.ai.enums.Difficulty;
import oogasalad.engine.model.ai.enums.WinType;
import oogasalad.engine.model.ai.evaluation.StateEvaluator;
import oogasalad.engine.model.ai.evaluation.meta.SeekEquality;
import oogasalad.engine.model.ai.evaluation.patterns.DiffBasedPatternProximityEvaluator;
import oogasalad.engine.model.ai.evaluation.patterns.Pattern;
import oogasalad.engine.model.ai.evaluation.totals.TotalPieces;
import oogasalad.engine.model.ai.searchTypes.AlphaBetaSearcher;
import oogasalad.engine.model.ai.searchTypes.MinMaxSearcher;
import oogasalad.engine.model.ai.searchTypes.searchersForDifficulty.RandomSearcher;
import oogasalad.engine.model.ai.searchTypes.Selects;
import oogasalad.engine.model.board.Piece;
import org.jetbrains.annotations.NotNull;

// TODO: clean this up - use reflection
public class SelectorFactory {

//  public static Selects makeSelector(Difficulty difficulty, WinType winType, int playerNumber, AIOracle aiOracle, Collection<Pattern> patterns) {
//    return difficulty.equals(Difficulty.RANDOM)? makeRandomSelector(aiOracle) : makeNonRandomSelector(difficulty, winType, playerNumber, aiOracle, patterns);
//  }

public static Selects makeSelector(Difficulty difficulty, WinType winType, int playerNumber, AIOracle aiOracle, Collection<Pattern> patterns) {
  String filePath = SearcherFinder.getFullyQualifiedClassName(difficulty);

  Selects selects = null;

  try {
    Class selectsClass = Class.forName(filePath);
    if(difficulty.equals(Difficulty.RANDOM)) {
      Constructor<Selects> selectsConstructor = getConstructor(selectsClass, winType);
      selects = selectsConstructor.newInstance(aiOracle);
    }
  } catch (Exception e) {
    throw new RuntimeException("bad");
  }
  return selects;
}

  private static Constructor getConstructor(Class selectsClass, WinType winType) throws NoSuchMethodException {

    return selectsClass.getConstructor(AIOracle.class);
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

  private static Selects makeRandomSelector(AIOracle aiOracle) {
    var filePath = SearcherFinder.getFullyQualifiedClassName(Difficulty.RANDOM);
    Selects selects = null;
    try {
      Class selectsClass = Class.forName(filePath);
      Constructor<Selects> selectsConstructor = getConstructor(selectsClass;
      selects = selectsConstructor.newInstance(aiOracle);
    } catch (Exception e) {
      throw new RuntimeException("bad");
    }
    return selects;
  }

//  private static Selects makeNonRandomSelector(Difficulty difficulty, WinType winType, int playerNumber, AIOracle aiOracle, Collection<Pattern> patterns) {
//    StateEvaluator evaluator = getEvaluator(winType, patterns);
//    return switch (difficulty) {
//      case EASY -> getEasyEvaluator(evaluator, playerNumber, aiOracle);
//      case MEDIUM -> getMediumEvaluator(evaluator, playerNumber, aiOracle);
//      case HARD -> getHardEvaluator(evaluator, playerNumber, aiOracle);
//      case EXPERT -> getExpertEvaluator(evaluator, playerNumber, aiOracle);
//      case ADAPTIVE -> getAdaptiveEvaluator(evaluator, playerNumber, aiOracle);
//      default -> throw new IllegalStateException("Unexpected value: " + difficulty);
//    };
//  }
//
//  private static Selects getAdaptiveEvaluator(StateEvaluator evaluator, int playerNumber, AIOracle aiOracle) {
//    boolean isPlayerOne = playerNumber== Piece.PLAYER_ONE;
//    StateEvaluator adaptiveEvaluator = new SeekEquality(evaluator, isPlayerOne, !isPlayerOne);
//    return new MinMaxSearcher(DifficultyDepthConstants.ADAPTIVE, adaptiveEvaluator, aiOracle);
//  }
//
//  private static Selects getExpertEvaluator(StateEvaluator evaluator, int playerNumber, AIOracle aiOracle) {
//    return new AlphaBetaSearcher(DifficultyDepthConstants.EXPERT, evaluator, aiOracle);
//  }
//
//  private static Selects getHardEvaluator(StateEvaluator evaluator, int playerNumber, AIOracle aiOracle) {
//    return new MinMaxSearcher(DifficultyDepthConstants.HARD, evaluator, aiOracle);
//  }
//
//  private static Selects getMediumEvaluator(StateEvaluator evaluator, int playerNumber, AIOracle aiOracle) {
//    return new MinMaxSearcher(DifficultyDepthConstants.MEDIUM, evaluator, aiOracle);
//  }
//
//  private static Selects getEasyEvaluator(StateEvaluator evaluator, int playerNumber, AIOracle aiOracle) {
//    return new MinMaxSearcher(DifficultyDepthConstants.EASY, evaluator, aiOracle);
//  }
//
//  private static StateEvaluator getEvaluator(WinType winType, Collection<Pattern> patterns) {
//    return winType.equals(WinType.TOTAL)? new TotalPieces(): new DiffBasedPatternProximityEvaluator(TreeSet.ofAll(patterns));
//
//  }
//

}