package oogasalad.engine.model.utilities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UtilitiesTest {

  @Test
  void testIsPositive() {
    Assertions.assertTrue(Utilities.isPositive(1));
    Assertions.assertTrue(Utilities.isPositive(99));
    Assertions.assertFalse(Utilities.isPositive(0));
    Assertions.assertFalse(Utilities.isPositive(-100));
    Assertions.assertFalse(Utilities.isPositive(-1));
    Assertions.assertFalse(Utilities.isPositive(-10));
  }

  @Test
  void isNegative() {
    Assertions.assertTrue(Utilities.isNegative(-1));
    Assertions.assertTrue(Utilities.isNegative(-100));
  }

  @Test
  void isNonNegative() {
    Assertions.assertFalse(Utilities.isNonNegative(-1));
    Assertions.assertFalse(Utilities.isNonNegative(-100));
    Assertions.assertTrue(Utilities.isNonNegative(0));
  }

  @Test
  void isNonPositive() {
    Assertions.assertTrue(Utilities.isNonPositive(-1));
    Assertions.assertTrue(Utilities.isNonPositive(0));
    Assertions.assertTrue(Utilities.isNonPositive(-100));
  }
}