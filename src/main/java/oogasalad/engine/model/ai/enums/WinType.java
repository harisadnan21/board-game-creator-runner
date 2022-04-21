package oogasalad.engine.model.ai.enums;

import java.lang.reflect.Constructor;
import java.util.Collection;
import oogasalad.engine.model.ai.evaluation.StateEvaluator;
import oogasalad.engine.model.ai.evaluation.patterns.CachingPatternEvaluator;
import oogasalad.engine.model.ai.evaluation.patterns.Pattern;
import oogasalad.engine.model.ai.evaluation.totals.TotalPieces;

public enum WinType {
  TOTAL,
  PATTERN
}
