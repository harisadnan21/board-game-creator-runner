package oogasalad.engine.model.parser;

import java.util.IllegalFormatConversionException;
import oogasalad.builder.view.property.PropertyEditor;
import oogasalad.engine.model.parser.exception.ParameterFormatException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Basic class for parsing expressions with variables into integers
 * @author Jake Heller
 */
public class ValueParser {

  private static final Logger LOG = LogManager.getLogger(ValueParser.class);
  public static final String ADDITION = "+";
  public static final String VARIABLE = "i";
  public static final String MINUS = "-";

  public static int getValue(String expression, int variable) {
    try {
      int value;
      if (!expression.contains(VARIABLE)) {
        value = Integer.parseInt(expression.strip());
      }
      else {
        String variableString;
        int addedValue;
        if (expression.contains(ADDITION)) {
          int index = expression.indexOf(ADDITION);
          variableString = expression.substring(0, index);
          addedValue = Integer.parseInt(expression.substring(index+1).strip());
        }
        else {
          variableString = expression;
          addedValue = 0;
        }
        int variableExpressionValue = getVariable(variableString, variable);
        value = variableExpressionValue + addedValue;
      }
      return value;
    } catch (Exception e) {
      throw new ParameterFormatException(e);
    }
  }

  private static int getVariable(String variableString, int variableValue) {
    return variableString.strip().contains(MINUS) ? -1 * variableValue : variableValue;
  }
}
