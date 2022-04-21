package oogasalad.builder.view.tab;

import javafx.scene.Node;
import oogasalad.builder.view.callback.CallbackDispatcher;


public class HelpTab extends BasicTab{
  public static String HELP = "help";

  public HelpTab(CallbackDispatcher callbackDispatcher){
    super(HELP, callbackDispatcher);

  }
  @Override
  protected Node setupRightSide() {
    return null;
  }

  @Override
  protected Node setupLeftSide() {
    return null;
  }

  @Override
  public void loadElements() {

  }
}
