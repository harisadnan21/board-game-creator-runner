package oogasalad.builder.view.tab;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import oogasalad.builder.model.property.IntegerProperty;
import oogasalad.builder.model.property.StringProperty;
import oogasalad.builder.view.BuilderView;
import oogasalad.builder.view.callback.GetElementNamesCallback;
import oogasalad.builder.view.callback.GetElementPropertyByKeyCallback;
import oogasalad.builder.view.callback.GetPropertiesCallback;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

public class TabTests extends DukeApplicationTest {
  private BuilderView builderView;
  @Override
  public void start(Stage stage) {
    builderView = new BuilderView(stage);
    builderView.registerCallbackHandler(GetElementNamesCallback.class, cb -> List.of("test"));
    builderView.registerCallbackHandler(
        GetPropertiesCallback.class, cb -> cb.type().equals("piece") ? List.of(
            new IntegerProperty("required-id", 0, "oogasalad.builder.view.property.IntegerSelector")) :
            List.of(new StringProperty("required-type", "Place",
                "oogasalad.builder.view.property.DropDown"), new IntegerProperty("Place-x", 0, "oogasalad.builder.view.property.IntegerSelector")));
    builderView.registerCallbackHandler(GetElementPropertyByKeyCallback.class,
        cb -> cb.key().equals("image") ? "checkers/pieces/normalWhite.png" : null);
    clickOn("#loginButton");
  }

  @Test
  public void testAddPiece() {
    clickOn("#pieceTab");
    clickOn("#new-pieceButton");

    var intSelector = lookup("#integerSelector-id").queryAs(Spinner.class);
    intSelector.getEditor().setText("" + 1);
    intSelector.commitValue();
    var nameField = lookup("#nameField-piece").queryAs(TextField.class);
    writeTo(nameField, "testCell");
    clickOn("#save-piece");
    assertTrue(lookup("#testCellCell").tryQuery().isPresent());
  }

  @Test
  public void newPieceButtonWork() {
    clickOn("#pieceTab");
    clickOn("#new-pieceButton");
    assertTrue(lookup("#integerSelector-id").tryQuery().isPresent());
  }

  @Test
  public void newActionButtonWork() {
    clickOn("#actionTab");
    clickOn("#new-actionButton");
    assertTrue(lookup("#dropDown-type").tryQuery().isPresent());
  }

  @Test
  public void testHelpPage(){
    clickOn("#helpTab");
    assertEquals("", lookup("#helpBox").queryAs(TextArea.class).getText());
    clickOn("#boardButton");
    assertNotEquals("", lookup("#helpBox").queryAs(TextArea.class).getText());
    clickOn("#pieceButton");
    assertNotEquals("", lookup("#helpBox").queryAs(TextArea.class).getText());
    clickOn("#actionButton");
    assertNotEquals("", lookup("#helpBox").queryAs(TextArea.class).getText());
  }
}
