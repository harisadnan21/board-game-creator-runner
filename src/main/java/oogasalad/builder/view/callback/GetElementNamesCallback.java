package oogasalad.builder.view.callback;

import java.util.Collection;

public record GetElementNamesCallback(String type) implements Callback<Collection<String>> {
}
