package oogasalad.builder.view.callback;

/**
 * Callback for finding the background color of a cell
 *
 * @author Shaan Gondalia
 */
public record FindCellBackgroundCallback(int x, int y) implements Callback<String> {

}
