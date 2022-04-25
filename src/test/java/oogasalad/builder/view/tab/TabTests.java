package oogasalad.builder.view.tab;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import oogasalad.builder.model.property.IntegerProperty;
import oogasalad.builder.model.property.StringListProperty;
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
        GetPropertiesCallback.class, cb -> switch (cb.type()) {
          case "piece" -> List.of(new IntegerProperty("required-id", 0,
              "oogasalad.builder.view.property.IntegerSelector"));
          case "condition" -> List.of(new StringListProperty("required-actions", "action1,action2",
                  "oogasalad.builder.view.property.ActionListSelector"),
              new StringListProperty("required-conditions", "",
                  "oogasalad.builder.view.property.ConditionListSelector"));
          default -> List.of(new StringProperty("required-type", "Place",
                  "oogasalad.builder.view.property.DropDown"),
              new IntegerProperty("Place-col", 0,
                  "oogasalad.builder.view.property.IntegerSelector"));
        });
    builderView.registerCallbackHandler(GetElementPropertyByKeyCallback.class,
        cb -> cb.key().equals("image") ? "checkers/pieces/normalWhite.png" : null);
    //clickOn("#loginButton");
  }

//  @Test
//  public void testAddPiece() {
//    clickOn("#pieceTab");
//    clickOn("#new-pieceButton");
//
//    var intSelector = lookup("#integerSelector-id").queryAs(Spinner.class);
//    intSelector.getEditor().setText("" + 1);
//    intSelector.commitValue();
//    var nameField = lookup("#nameField-piece").queryAs(TextField.class);
//    writeTo(nameField, "testCell");
//    clickOn("#save-piece");
//    assertTrue(lookup("#testCellCell").tryQuery().isPresent());
//  }

//  @Test
//  public void newPieceButtonWork() {
//    clickOn("#pieceTab");
//    clickOn("#new-pieceButton");
//    assertTrue(lookup("#integerSelector-id").tryQuery().isPresent());
//  }
//
//  @Test
//  public void newActionButtonWork() {
//    clickOn("#actionTab");
//    clickOn("#new-actionButton");
//    assertTrue(lookup("#dropDown-type").tryQuery().isPresent());
//  }
//
//  @Test
//  public void testGameElementListSelector() {
//    clickOn("#conditionTab");
//    clickOn("#new-conditionButton");
//    assertFalse(lookup("#gameElementListSelector-actions").tryQuery().isPresent());
//    assertTrue(lookup("#gameElementListSelector-actions-listView").queryListView().getItems().containsAll(List.of("action1", "action2")));
//    select(lookup("#gameElementListSelector-actions-comboBox").queryComboBox(), "test");
//    assertTrue(lookup("#gameElementListSelector-actions-listView").queryListView().getItems().containsAll(List.of("action1", "action2","test")));
//
//    assertTrue(lookup("#gameElementListSelector-conditions").tryQuery().isPresent());
//    assertTrue(lookup("#gameElementListSelector-conditions-listView").queryListView().getItems().isEmpty());
//    select(lookup("#gameElementListSelector-conditions-comboBox").queryComboBox(), "test");
//    assertTrue(lookup("#gameElementListSelector-conditions-listView").queryListView().getItems().containsAll(List.of("test")));
//    clickOn("#gameElementListSelector-conditions-listView-test-delete");
//    assertTrue(lookup("#gameElementListSelector-conditions-listView").queryListView().getItems().isEmpty());
//  }
//
//  @Test
//  public void testHelpPage(){
//    clickOn("#helpTab");
//    assertEquals("", lookup("#helpBox").queryAs(TextArea.class).getText());
//    clickOn("#boardButton");
//    assertNotEquals("", lookup("#helpBox").queryAs(TextArea.class).getText());
//    clickOn("#pieceButton");
//    assertNotEquals("", lookup("#helpBox").queryAs(TextArea.class).getText());
//    clickOn("#actionButton");
//    assertNotEquals("", lookup("#helpBox").queryAs(TextArea.class).getText());
//  }
}
