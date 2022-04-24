package oogasalad.engine.model.rule;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.engine.Oracle;
import oogasalad.engine.model.logicelement.conditions.Condition;
import oogasalad.engine.model.logicelement.conditions.position_independent_conditions.True;
import oogasalad.engine.model.logicelement.winner.PlayerWins;
import oogasalad.engine.model.logicelement.winner.WinDecision;
import oogasalad.engine.model.rule.terminal_conditions.EndRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EndRuleTest {

  Oracle myOracle;
  EndRule rule;

  @BeforeEach
  void setup() {
    WinDecision playerWins = new PlayerWins(new int[]{1});
    Condition truee = new True(new int[0]);
    rule = new EndRule(new Condition[]{truee}, playerWins);
    myOracle = new Oracle(new ArrayList<>(), Arrays.stream((new EndRule[]{rule})).toList(), 2);
  }

  @Test
  void testTrue() {
    Board board = new Board(3,3);
    assertTrue(myOracle.isWinningState(board));
    assertEquals(1, myOracle.getWinner(board));
  }

}
