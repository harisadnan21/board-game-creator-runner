package oogasalad.builder.view;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import javafx.stage.Stage;
import oogasalad.builder.model.property.IntegerProperty;
import oogasalad.builder.model.property.StringProperty;
import oogasalad.builder.view.callback.FindCellBackgroundCallback;
import oogasalad.builder.view.callback.FindPieceAtCallback;
import oogasalad.builder.view.callback.GetElementNamesCallback;
import oogasalad.builder.view.callback.GetElementPropertiesCallback;
import oogasalad.builder.view.callback.GetHeightCallback;
import oogasalad.builder.view.callback.GetPropertiesCallback;
import oogasalad.builder.view.callback.GetWidthCallback;;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;
import static org.junit.jupiter.api.Assertions.*;

class BuilderViewTest extends DukeApplicationTest {

  private static final int X_DIM = 10;
  private static final int Y_DIM = 14;
  private static final String CHECKERS_PATH = "/games/checkers";

  private BuilderView builderView;
  @Override
  public void start(Stage stage) {
    builderView = new BuilderView(stage);
    builderView.registerCallbackHandler(GetHeightCallback.class, cb -> 5);
    builderView.registerCallbackHandler(GetWidthCallback.class, cb -> 5);
    builderView.registerCallbackHandler(FindCellBackgroundCallback.class, cb -> "0xffffffff");
    builderView.registerCallbackHandler(FindPieceAtCallback.class, cb -> "empty");
    builderView.registerCallbackHandler(GetElementNamesCallback.class, cb-> List.of("test"));
    builderView.registerCallbackHandler(GetElementPropertiesCallback.class, cb -> List.of());
    builderView.registerCallbackHandler(
        GetPropertiesCallback.class, cb -> cb.type().equals("piece") ? List.of(
            new IntegerProperty("required-id", 0, "oogasalad.builder.view.property.IntegerSelector")) : List.of(
             new StringProperty("required-author", "me", "oogasalad.builder.view.property.StringField"),
            new StringProperty("required-description", "descriptionTEST", "oogasalad.builder.view.property.TextAreaField" )));
    //clickOn("#loginButton");
  }

  @Test
  void testLogin() {
    // We know the board tab will exist after logging in, so just use that to check
    assertTrue(lookup("#boardTab").tryQuery().isPresent());
    assertTrue(lookup("#pieceTab").tryQuery().isPresent());
    assertTrue(lookup("#actionTab").tryQuery().isPresent());

  }


  @Test
  public void testLoading(){
    //clickOn("#loadGameButton");
    builderView.getAllTabs().loadAllTabs();
    clickOn("#pieceTab");
    assertTrue(lookup("#testCell").tryQuery().isPresent());
   // assertEquals(Color.valueOf("0xffffffff"),BoardTabAccessor.getColor(lookup("#boardTabPane").queryAs(BoardTab.class), 1));
  }

}
