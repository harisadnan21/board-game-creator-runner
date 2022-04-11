package oogasalad.builder.view.callback;

public interface CallbackHandler<R, C extends Callback<R>> {

    R handle(C callback);

}
