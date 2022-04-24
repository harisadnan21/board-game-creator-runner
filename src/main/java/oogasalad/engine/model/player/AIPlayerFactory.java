package oogasalad.engine.model.player;

import java.util.Collection;
import oogasalad.engine.model.ai.AIOracle;
import oogasalad.engine.model.ai.AIPlayer;
import oogasalad.engine.model.ai.SelectorFactory;
import oogasalad.engine.model.ai.enums.Difficulty;
import oogasalad.engine.model.ai.enums.WinType;
import oogasalad.engine.model.ai.evaluation.patterns.Pattern;
import oogasalad.engine.model.ai.moveSelection.Selects;
import oogasalad.engine.model.player.AbstractPlayer;

public class AIPlayerFactory {

  public AbstractPlayer makeAIPlayer(Difficulty difficulty, WinType winType, int playerNumber, AIOracle aiOracle, Collection<Pattern> patterns) {
    Selects selects = SelectorFactory.makeSelector(difficulty, winType, playerNumber, aiOracle, patterns);
    return new AIPlayer(playerNumber, selects);
  }

}
