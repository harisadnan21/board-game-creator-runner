package oogasalad.engine.model.conditions;
import oogasalad.engine.model.actions.winner.Winner;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.conditions.board_conditions.BoardCondition;

/**
 * Class to hold a draw condition. This happens when there is no moves left to be performed from
 * both players. The game will end.
 * @author Haris Adnan
 *
 */
public class DrawCondition extends EndCondition{
  private BoardCondition[] myEndConditions;
  private Winner myWinDecision;

  public DrawCondition(BoardCondition[] endConditions, Winner winDecision){
    super();
    myWinDecision = winDecision;
    myEndConditions = endConditions;
  }
  public void setWinCondition(){
    myWinDecision = null;
  }
}
