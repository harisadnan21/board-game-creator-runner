package oogasalad.builder.view.callback;

public record GetElementPropertyByKeyCallback(String type, String name, String key) implements Callback<String> {
}
