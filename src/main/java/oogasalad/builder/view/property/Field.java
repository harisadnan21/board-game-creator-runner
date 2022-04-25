package oogasalad.builder.view.property;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import oogasalad.builder.model.property.Property;
import oogasalad.builder.view.ViewResourcesSingleton;
import oogasalad.builder.view.callback.CallbackDispatcher;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The most basic form of a PropertySelector, essentially just a wrapper for a JavaFX text box.
 *
 * @author Shaan Gondalia
 */
public abstract class Field implements PropertySelector{

  private final Property property;
  private final TextField valueField;
  private final Logger logger = LogManager.getLogger(Field.class);

  /**
   * Creates a new Field, which is the most simple property selector (Just a textfield)
   *
   * @param property the property that will be "filled in" by the Field
   */
  public Field(Property property, CallbackDispatcher dispatcher){
    this.property = property;
    valueField = new TextField(tryGetResourceString(property.valueAsString()));
    if (!property.valueAsString().equals(property.defaultValueAsString())){
      valueField.setText(property.valueAsString());
    }
    valueField.setId("stringField-" + property.shortName());
  }

  /**
   * Trys to get key from ViewResourceSingleton and logs if it cant
   * @return String mapped value or key if not
   */
  protected String tryGetResourceString(String key){
    try{ return ViewResourcesSingleton.getInstance().getString(key);
    }catch(Exception e){
      logger.log(Level.ERROR, e.getMessage());
    }
    return key;
  }
  /**
   * Returns a JavaFX Node that will be displayed to the user next to the property label
   *
   * @return a Node that will be shown to the user containing UI for entering a property value
   */
  @Override
  public Node display() {
    return valueField;
  }

  /**
   * Returns a populated property with the correct value, name, and form
   *
   * @return a populated property with the correct value, name, and form
   */
  @Override
  public abstract Property getProperty();

  /**
   * Returns the property that was passed in during construction of the field
   *
   * @return the property that was passed in during construction of the field
   */
  protected Property property() {
    return property;
  }

  /**
   * Returns the text that was input into the value field
   *
   * @return the property that was passed in during construction of the field
   */
  protected String text() {
    return valueField.getText();
  }
}
