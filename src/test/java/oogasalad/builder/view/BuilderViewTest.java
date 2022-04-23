package oogasalad.builder.view;

import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import oogasalad.builder.model.exception.MissingRequiredPropertyException;
import oogasalad.builder.view.callback.ClearCellCallback;
import oogasalad.builder.view.callback.GetElementNamesCallback;
import oogasalad.builder.view.callback.GetElementPropertyByKeyCallback;
import oogasalad.builder.view.callback.MakeBoardCallback;
import oogasalad.builder.view.callback.PlacePieceCallback;
import oogasalad.builder.view.tab.boardTab.BoardTab;
import oogasalad.builder.view.tab.boardTab.BoardTabAccessor;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.util.List;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;


class BuilderViewTest extends DukeApplicationTest {
    private static final int X_DIM = 10;
    private static final int Y_DIM = 14;

    private BuilderView builderView;
    private Stage stage;
    private Stack<PlacePieceCallback> piecePlacedCB = new Stack<>();
    private Stack<ClearCellCallback> pieceErasedCB = new Stack<>();
    private Stack<MakeBoardCallback> makeBoardCB = new Stack<>();

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        builderView = new BuilderView(stage);
        builderView.registerCallbackHandler(GetElementNamesCallback.class, cb -> List.of("test"));
        builderView.registerCallbackHandler(GetElementPropertyByKeyCallback.class, cb -> cb.key().equals("image") ? "checkers/pieces/normalWhite.png" : null);
        builderView.registerCallbackHandler(PlacePieceCallback.class, cb -> {piecePlacedCB.push(cb); return null;});
        builderView.registerCallbackHandler(ClearCellCallback.class, cb -> {pieceErasedCB.push(cb); return null;});
        builderView.registerCallbackHandler(MakeBoardCallback.class, cb -> {makeBoardCB.push(cb); return null;});
        clickOn("#loginButton");
    }

    @Test
    void testLogin() {
        // We know the board tab will exist after logging in, so just use that to check
        assertTrue(lookup("#boardTab").tryQuery().isPresent());
        assertTrue(lookup("#pieceTab").tryQuery().isPresent());
        assertTrue(lookup("#actionTab").tryQuery().isPresent());

    }

    void boardSetup() {
        var xSpinner = lookup("#xDimEntry").queryAs(Spinner.class);
        var ySpinner = lookup("#yDimEntry").queryAs(Spinner.class);
        xSpinner.getEditor().setText("" + X_DIM);
        ySpinner.getEditor().setText("" + Y_DIM);
        xSpinner.commitValue();
        ySpinner.commitValue();
        lookup("#colorPickerA").queryAs(ColorPicker.class).setValue(Color.BLUE);
        select(lookup("#boardTypePicker").queryAs(ComboBox.class), "Checkers");
        clickOn("#drawBoard");
    }

    @Test
    void testDrawBoard() {
        makeBoardCB.clear();
        boardSetup();
        assertEquals(1, makeBoardCB.size());
        assertEquals(new MakeBoardCallback(X_DIM, Y_DIM), makeBoardCB.get(0));
        assertEquals(BoardTabAccessor.getColor(lookup("#boardTab").queryAs(BoardTab.class), 1), Color.BLUE);
    }

    @Test
    public void testAddBoardPieces() {
        boardSetup();
        select(lookup("#choosePieceBox").queryAs(ComboBox.class), "test");
        piecePlacedCB.clear();
        pieceErasedCB.clear();
        clickOn("#builderBoard");
        assertEquals(1, piecePlacedCB.size());
        assertEquals(0, pieceErasedCB.size());
        assertEquals(new PlacePieceCallback(4, 6, "test"), piecePlacedCB.get(0));
    }

    @Test
    public void testEraseBoardPieces() {
        testAddBoardPieces();
        clickOn("#eraserButton");
        piecePlacedCB.clear();
        pieceErasedCB.clear();
        clickOn("#builderBoard");
        assertEquals(0, piecePlacedCB.size());
        assertEquals(1, pieceErasedCB.size());
        assertEquals(new ClearCellCallback(4, 6), pieceErasedCB.get(0));
    }

    @Test
    public void testClearBoardPieces() {
        testAddBoardPieces();
        piecePlacedCB.clear();
        pieceErasedCB.clear();
        clickOn("#clearPieces");
        assertEquals(0, piecePlacedCB.size());
        // We don't care if it clears all the cells or just the minimum it has to
        assertTrue(pieceErasedCB.size() > 1);
        assertTrue(pieceErasedCB.contains(new ClearCellCallback(4, 6)));
    }

    @Test
    public void addPiece(){
        clickOn("#new-piece");

        var playerSelector = lookup("#integerSelector-player").queryAs(Spinner.class);
        playerSelector.getEditor().setText(""+1);
        playerSelector.commitValue();
        var idSelector = lookup("#integerSelector-id").queryAs(Spinner.class);
        idSelector.getEditor().setText(""+1);
        idSelector.commitValue();
        //HOW DO I SET FILECHOOSER PROPERLY AND GIVE IT A VALUE?
        clickOn("#fileSelector-piece");
        clickOn("#save-piece");
        assertTrue(lookup("#elementList-piece").queryAs(GameElementList.class).hasProperties());
    }
    @Test
    public void newPieceButtonWork(){
        clickOn("#new-piece");
        assertTrue(lookup("#integerSelector-id").tryQuery().isPresent());
    }
    @Test
    public void newActionButtonWork(){
        clickOn("#new-action");
        assertTrue(lookup("#DropDown").tryQuery().isPresent());
    }

}
