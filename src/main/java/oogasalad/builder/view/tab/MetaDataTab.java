package oogasalad.builder.view.tab;

import oogasalad.builder.view.callback.CallbackDispatcher;

/**
 * Tab that allows users to edit the metadata of their game.
 *
 * TODO: Maybe switch this from a tab to a pop-up menu when the user tries to save a configuration
 *
 * @author Shaan Gondalia
 */
public class MetaDataTab extends GameElementTab {

  private static final String METADATA = "metadata";

  /**
   * Creates a new MetaDataTab
   *
   * @param dispatcher the callback dispatcher that allows communication with the controller
   */
  public MetaDataTab(CallbackDispatcher dispatcher) {
    super(dispatcher, METADATA);
  }
}
