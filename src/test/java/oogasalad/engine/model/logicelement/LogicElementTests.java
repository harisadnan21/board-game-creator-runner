package oogasalad.engine.model.logicelement;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import oogasalad.engine.model.logicelement.actions.Action;
import oogasalad.engine.model.logicelement.actions.Outflank;
import oogasalad.engine.model.logicelement.conditions.position_dependent_conditions.IsPieceTypeRay;
import org.junit.jupiter.api.Test;

public class LogicElementTests {

  @Test
  void canConstructOutflank() throws NoSuchMethodException {
    assertDoesNotThrow(() -> Outflank.class.getConstructor(int[].class));
  }

  @Test
  void canConstructIsPieceTypeRay() throws NoSuchMethodException {
    assertDoesNotThrow(() -> IsPieceTypeRay.class.getConstructor(int[].class));
  }

}
