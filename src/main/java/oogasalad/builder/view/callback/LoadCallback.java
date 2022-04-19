package oogasalad.builder.view.callback;

import java.io.File;

/**
 * Callback that contains a directory to load a configuration from.
 *
 * @author Shaan Gondalia
 */
public record LoadCallback(File directory) implements Callback<Void> {
}
