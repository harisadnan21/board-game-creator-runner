package oogasalad.engine.model.utilities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UtilitiesTest {

  @Test
  void isPositive() {
    Assertions.assertTrue(Utilities.isPositive(1));
    Assertions.assertTrue(Utilities.isPositive(99));
    Assertions.assertFalse(Utilities.isPositive(0));
    Assertions.assertFalse(Utilities.isPositive(-100));
    Assertions.assertFalse(Utilities.isPositive(-1));
    Assertions.assertFalse(Utilities.isPositive(-10));
  }

}