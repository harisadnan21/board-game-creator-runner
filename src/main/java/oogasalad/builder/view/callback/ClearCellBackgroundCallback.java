package oogasalad.builder.view.callback;

/**
 * Callback for clearing the background color of a cell
 *
 * @author Shaan Gondalia
 */
public record ClearCellBackgroundCallback (int x, int y) implements Callback<Void> {

}
