package oogasalad.builder.view;

import static org.junit.jupiter.api.Assertions.*;

import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;
import static org.junit.jupiter.api.Assertions.*;

import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;


class BuilderViewTest extends DukeApplicationTest {


    private BuilderView builderView;

    @Override
    public void start(Stage stage) {
        builderView = new BuilderView(stage);
    }

//    @Test
//    public void clickAddPieceTest() {

//          clickOn(0, 0, MouseButton.PRIMARY);
//        int[][] boardConfig = builderView.getBoardConfig();
//
//        assertEquals(1, boardConfig[0][0]);

    //}
}

//  public static final String TITLE = "Slogo Application";
//  public static final String LANGUAGE = "English";
//  public static final Dimension DEFAULT_SIZE = new Dimension(1500, 1000);
//  private static final String LANGUAGE_RESOURCE_PATH = "view";
//  private static final String EXAMPLE_PROGRAMS_PATH = "/examples";
//  private static final String textTest = "hello.";
//
//  private TextArea myShellArea;
//  private TextArea myScriptEditor;
//  private Button myPlayButton;
//  private ImageView myTurtle;
//  private Button myLoadButton;
//  private Button myResetButton;
//  private Button myAddButton;
//  private Button mySaveButton;
//
//
//
//
//
//  @Override
//  public void start(Stage stage) {
//
//    slogoGUI turtleSim = new slogoGUI(stage, LANGUAGE);
//    Scene scene = turtleSim.makeScene(DEFAULT_SIZE.width, DEFAULT_SIZE.height);
//
//    // give the window a title
//    stage.setTitle(TITLE);
//    // add our user interface components to Frame and show it
//    stage.setScene(scene);
//    //stage.setFullScreen(true);
//    stage.show();
//
//    myShellArea = lookup("#textArea").query();
//    myScriptEditor = lookup("#editorArea").query();
//    myPlayButton = lookup("#PlayButton").query();
//    myTurtle = lookup("#TurtleImage").query();
//    myLoadButton = lookup("#LoadFile").query();
//    mySaveButton = lookup("#SaveFile").query();
//    myResetButton = lookup("#resetSlogo").query();
//    myAddButton = lookup("#addSlogo").query();
//
//
//
//  }
//
//
//  @Test
//  void testShellInput() {
//
//    writeTo(myShellArea, textTest);
//    assertEquals(myShellArea.getText(), textTest);
//
//
//  }
//
//  @Test
//  void testTextEditor() {
//
//    writeTo(myScriptEditor, textTest);
//    assertEquals(myScriptEditor.getText(), textTest);
//
//  }
//
//  /**
//   * Tests to see if the view can take an input of fd 50 and move the turtle accordingly
//   *
//   * @throws InterruptedException
//   */
//  @Test
//  void testFd50() throws InterruptedException {
//    String fd50 = "fd 50";
//    double initialY = myTurtle.getY();
//    writeTo(myScriptEditor, fd50);
//    clickOn(myPlayButton);
//    Thread.sleep(1000);
//    double finalY = myTurtle.getY();
//    assertEquals(initialY + 50, finalY);
//
//  }
//
//
//  /**
//   * Tests the shells functionality (the press command is not working which limits our coverage)
//   *
//   * @throws InterruptedException
//   */
//  @Test
//  void shellTesting() throws InterruptedException {
//    String initShell = "slogo_team02 % ";
//    String shellTest1 = "fd 50";
//    String shellTest2 = "rt 90";
//    appendTo(myShellArea, shellTest1);
//    press(KeyCode.ENTER);
//    Thread.sleep(1000);
//    appendTo(myShellArea, shellTest2);
//    Thread.sleep(1000);
//    press(KeyCode.ENTER);
//    press(KeyCode.UP);
//    press(KeyCode.UP);
//    assertEquals(myShellArea.getText(), initShell + shellTest1);
//    press(KeyCode.DOWN);
//    assertEquals(myShellArea.getText(), initShell + shellTest2);
//    Thread.sleep(1000);
//
//  }
//
//  @Test
//  void testRt90Fd50(){
//    String rt90fd50 = "rt 90\nfd50";
//    double initX = myTurtle.getX();
//    writeTo(myScriptEditor, rt90fd50);
//    clickOn(myPlayButton);
//    double finalX = myTurtle.getX();
//
//    assertEquals(finalX, initX + 50);
//  }
//
//  @Test
//  void loadButtonPressed(){
//    clickOn(myLoadButton);
//
//    // happy path
//    //clickOn(400, 1000);  // click on right file
//
//
//    // bad path
//    //clickOn(100, 1000); // click on wrong file
//
//  }
//
//
//
//  @Test
//  void resetButtonPressed(){
//    clickOn(myResetButton);
//
//    // happy path - new stage created
//
//
//
//  }
//
//  @Test
//  void addButtonPressed(){
//    clickOn(myAddButton);
//  }
//
//  @Test
//  void saveFile(){
//
//
//    writeTo(myScriptEditor, "helloWorld");
//    clickOn(mySaveButton);
//    TextField titleInput = lookup("#titleInput").query();
//    Button submitInput = lookup("#SubmitFile").query();
//    writeTo(titleInput, "testFile");
//    clickOn(submitInput);
//
//
//  }
//
//
//  /**
//   * Tests to see if an exception is thrown when there are too many inputs
//   *
//   * @throws InterruptedException
//   */
//  @Test
//  void showException() throws InterruptedException {
//    writeTo(myScriptEditor, "fd 50 50 50 50");
//    clickOn(myPlayButton);
//    Thread.sleep(500);
//    DialogPane alert = lookup("#alert").query();
//    assertTrue(alert.getContentText().contains("more constants than needed"));
//  }
//
//  void testUploadFile() {
//
//  }
//=======
//  private BuilderView builderView;
//  private BorderPane boardTab;
//
//  @Override
//  public void start(Stage stage) {
//    builderView =  new BuilderView(stage);
//
//    boardTab = lookup("#boardTab").query();
//  }
//
//  @Test
//  public void clickAddPieceTest() {
//
//    clickOn(boardTab, 1,1);
//    sleep(500);
//    int[][] boardConfig = builderView.getBoardConfig();
//
//    assertEquals(1, boardConfig[0][0]);
//
//  }
//
//}
//>>>>>>> master

