package oogasalad.builder.view.callback;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CallbacksTest {
    private CallbackDispatcher dispatcher;
    private TestCB testCallback = new TestCB();
    private CallbackHandler<String, TestCB> handler1 = (cb) -> "result 1";
    private CallbackHandler<String, TestCB> handler2 = (cb) -> "result 2";

    @BeforeEach
    void setup() {
        dispatcher = new CallbackDispatcher();
    }

    @Test
    void testCallCallback() {
        assertTrue(dispatcher.call(testCallback).isEmpty());

        dispatcher.registerCallbackHandler(TestCB.class, handler1);
        assertTrue(dispatcher.call(testCallback).isPresent());
        assertEquals("result 1", dispatcher.call(testCallback).get());

        dispatcher.registerCallbackHandler(TestCB.class, handler2);
        assertTrue(dispatcher.call(testCallback).isPresent());
        assertEquals("result 2", dispatcher.call(testCallback).get());

        dispatcher.registerCallbackHandler(TestCB.class, null);
        assertTrue(dispatcher.call(testCallback).isEmpty());
    }

    @Test
    void testRegisterCallback() throws NoSuchFieldException, IllegalAccessException {
        Field handlerField = dispatcher.getClass().getDeclaredField("handlers");
        handlerField.setAccessible(true);
        var handlers = (Map<Class<? extends Callback<?>>, CallbackHandler<?,?>>)handlerField.get(dispatcher);
        assertFalse(handlers.containsKey(TestCB.class));

        dispatcher.registerCallbackHandler(TestCB.class, handler1);
        assertTrue(handlers.containsKey(TestCB.class));
        assertEquals(handler1, handlers.get(TestCB.class));

        dispatcher.registerCallbackHandler(TestCB.class, handler2);
        assertTrue(handlers.containsKey(TestCB.class));
        assertEquals(handler2, handlers.get(TestCB.class));
    }

}
