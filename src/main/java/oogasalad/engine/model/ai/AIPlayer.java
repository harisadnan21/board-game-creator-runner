package oogasalad.engine.model.ai;

import java.util.Collection;
import oogasalad.engine.model.ai.enums.Difficulty;
import oogasalad.engine.model.ai.enums.WinType;
import oogasalad.engine.model.ai.evaluation.patterns.Pattern;
import oogasalad.engine.model.ai.evaluation.StateEvaluator;
import oogasalad.engine.model.ai.searchTypes.Selects;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.engine.Choice;
import oogasalad.engine.model.player.Player;


public class AIPlayer extends Player {

  private final int playerNumber;
  private StateEvaluator stateEvaluator;
  private final Difficulty difficulty;
  private final AIOracle AIOracle;
  private Selects selector;


  public AIPlayer(int playerNumberForAI, StateEvaluator stateEvaluator, Difficulty difficulty, AIOracle AIOracle, WinType winType, Collection<Pattern> patterns) {
    super(null, null, null); // should be engine
    this.playerNumber = playerNumberForAI;
    this.stateEvaluator = stateEvaluator;
    this.difficulty = difficulty;
    this.AIOracle = AIOracle;
    this.selector = SelectorFactory.makeSelector(difficulty, winType, playerNumber, AIOracle, patterns); //FIX
  }

  public AIChoice chooseAction(Board board) {
    return selector.selectChoice(board);
  }


  @Override
  public void chooseMove() {
    Board board = super.getGameBoard();
    AIChoice AIChoice = this.chooseAction(board);
    super.executeMove(this, new Choice(null, null));
  }

  @Override
  public void onCellSelect(int i, int j) {
    throw new NoSuchMethodError("no");
  }
}
