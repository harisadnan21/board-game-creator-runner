package oogasalad.view;

import java.io.IOException;
import java.lang.reflect.Field;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

class SplashWelcomeTest extends DukeApplicationTest {

    SplashWelcome newWelcome;
    Stage s;

    @Override
    public void start (Stage stage) throws IOException {
        s = stage;
        newWelcome = new SplashWelcome();
    }

    @Test
    void test() throws NoSuchFieldException, IllegalAccessException {
        Field buttonField = SplashWelcome.class.getDeclaredField("engine");
        buttonField.setAccessible(true);
        Field stageField = SplashWelcome.class.getDeclaredField("stage");
        stageField.setAccessible(true);
        Button b = (Button)buttonField.get(newWelcome);
        Stage stage = (Stage)stageField.get(newWelcome);
        clickOn(b);
        assertEquals(678, stage.getHeight());
    }
}