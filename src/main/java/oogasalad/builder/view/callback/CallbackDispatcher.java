package oogasalad.builder.view.callback;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CallbackDispatcher {
    private Map<Class<? extends Callback<?>>, CallbackHandler<?,?>> handlers = new HashMap<>();

    public <R, C extends Callback<R>> void registerCallbackHandler(Class<C> callback, CallbackHandler<R, C> handler) {
        handlers.put(callback, handler);
    }

    public <R, C extends Callback<R>> Optional<R> call(C callback) {
        Class<?> clazz = callback.getClass();
        if(handlers.containsKey(clazz)&& handlers.get(clazz) != null) {
            return Optional.of(((CallbackHandler<R, C>)handlers.get(clazz)).handle(callback));
        }
        return Optional.empty();
    }

}
