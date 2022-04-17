package oogasalad.builder.view.callback;

import java.io.File;

public record SaveCallback(File file) implements Callback<Void> {
}
