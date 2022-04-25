package oogasalad.engine.model.player;

import java.util.ArrayList;
import java.util.Set;
import java.util.function.Consumer;
import oogasalad.engine.model.ai.enums.Difficulty;
import oogasalad.engine.model.ai.enums.WinType;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.engine.Oracle;

/**
 * Factory to crate a new Player
 * @see oogasalad.engine.controller.Controller
 * @author Robert Cranston
 */
public class PlayerFactory {
  private Oracle oracle;
  private Consumer<Set<Position>> setValidMarks;
  public PlayerFactory(Oracle oracle, Consumer<Set<Position>> setValidMarks){
    this.oracle = oracle;
    this.setValidMarks = setValidMarks;
  }

  /**
   * Creates a new Player based on the specified type
   * @param type tupe of player to be created
   * @return new Player
   */
  public Player create(String type){
    AIPlayerFactory factory = new AIPlayerFactory();
    if(!type.equals("human")){
      return factory.makeAIPlayer(Difficulty.valueOf(type), WinType.TOTAL, 1, oracle, new ArrayList<>());
    }
    return new HumanPlayer(oracle, null, setValidMarks);
  }
}
