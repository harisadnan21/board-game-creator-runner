package oogasalad.builder.view.callback;

/**
 * Callback for setting the background color of a cell
 *
 * @author Shaan Gondalia
 */
public record ColorCellBackgroundCallback (int x, int y, String color) implements Callback<Void> {

}
