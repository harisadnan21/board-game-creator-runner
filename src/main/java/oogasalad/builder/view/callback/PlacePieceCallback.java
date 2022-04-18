package oogasalad.builder.view.callback;

public record PlacePieceCallback(int x, int y, String piece) implements Callback<Void> {
}
