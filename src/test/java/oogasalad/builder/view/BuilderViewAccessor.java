package oogasalad.builder.view;


import oogasalad.builder.view.callback.CallbackDispatcher;
import oogasalad.builder.view.tab.AllTabs;

public class BuilderViewAccessor {

  public static AllTabs getAllTabs(BuilderView builderView) {
    return builderView.getAllTabs();
  }
  public static CallbackDispatcher getCallbackDispatcher(BuilderView builderView){
    return builderView.getCallbackDispatcher();
  }
}
