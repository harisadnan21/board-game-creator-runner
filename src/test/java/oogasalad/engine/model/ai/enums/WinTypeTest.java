package oogasalad.engine.model.ai.enums;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import org.junit.jupiter.api.Test;


class WinTypeTest {

  @Test
  void values() {
    WinType[] winTypes = WinType.values();
    assertTrue(Arrays.asList(winTypes).contains(WinType.TOTAL));
    assertTrue(Arrays.asList(winTypes).contains(WinType.PATTERN));
  }
}