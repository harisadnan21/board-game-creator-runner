package oogasalad.builder.view.tab;

import static oogasalad.builder.view.BuilderView.DEFAULT_RESOURCE_PACKAGE;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashSet;
import java.util.ResourceBundle;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import oogasalad.builder.model.exception.InvalidFormException;
import oogasalad.builder.view.ViewResourcesSingleton;
import oogasalad.builder.view.callback.CallbackDispatcher;

/**
 *
 * @author Mike Keohane
 */
public class AllTabs extends TabPane {
  public static final String TABS_LIST = "TabsList";
  public static final String TABS_PATH = "oogasalad.builder.view.tab.";
  private ResourceBundle tabsList;
  private CallbackDispatcher callbackDispatcher;
  private Collection<AbstractTab> tabs;

  public AllTabs(CallbackDispatcher callbackDispatcher){
    this.callbackDispatcher = callbackDispatcher;
    tabsList = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + TABS_LIST);
    tabs = new HashSet<>();
    createTabs();
  }

  private void createTabs() {
    for (String tabKey : tabsList.keySet()){
      this.getTabs().add(createTab(tabKey));
    }
    this.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
  }

  //Create a tab using reflection
  private Tab createTab(String tabNameKey) {
    try {
      Class<?> clss = Class.forName(TABS_PATH + tabsList.getString(tabNameKey));
      Constructor<?> ctor = clss.getDeclaredConstructor(CallbackDispatcher.class);
      AbstractTab createdTab = (AbstractTab) ctor.newInstance(callbackDispatcher);
      createdTab.setId(tabNameKey + "Tab");
      tabs.add(createdTab);
      return new Tab(ViewResourcesSingleton.getInstance().getString(tabNameKey), createdTab);
    } catch (NoSuchMethodException | ClassNotFoundException | InvocationTargetException |
        InstantiationException | IllegalAccessException e) {
      e.printStackTrace();
      throw new InvalidFormException(e.getMessage()); // TODO: Handle this properly
    }
  }

  /**
   * loads the elements for all of the tabs
   */
  public void loadAllTabs(){
    for (AbstractTab tab : tabs){
      tab.loadElements();
    }
  }
}
