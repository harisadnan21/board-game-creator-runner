package oogasalad.engine.model.utilities;


/**
 * Class for a key-value pair
 * @param <K> key
 * @param <V> value
 *
 * @author Jake Heller
 */
//TODO: could extend Tuple2 if you want because it has more built in functionality
public record Pair<K,V>(K key, V value) {

}

