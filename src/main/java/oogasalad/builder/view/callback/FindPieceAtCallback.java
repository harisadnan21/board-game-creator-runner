package oogasalad.builder.view.callback;

/**
 * Callback for finding the piece at a specified cell
 *
 * @author Mike Keohane
 */
public record FindPieceAtCallback(int x, int y) implements Callback<String> {

}
