package oogasalad.builder.model.element.factory;

import oogasalad.builder.controller.Property;
import oogasalad.builder.model.element.*;
import oogasalad.builder.model.exception.IllegalPropertyDefinitionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

class GameElementFactoryTest {
    private Map<Class<? extends GameElement>, GameElementFactory> factories;
    private TestMoveFactory testMoveFactory;
    private Collection<Property> testProperties = Set.of(new Property(String.class, "thingy", "hello there"),
            new Property(Integer.class, "name", "123"));

    private class TestMoveFactory extends GameElementFactory<Move> {
        public TestMoveFactory(String path) {
            super(path);
        }

        @Override
        public Move createElement(String name, Collection<Property> properties) {
            return new Move(name, properties);
        }
    }

    @BeforeEach
    void setup() {
        factories = new HashMap<>() {{
            put(Move.class, new MoveFactory());
            put(Piece.class, new PieceFactory());
            put(Rule.class, new RuleFactory());
            put(WinCondition.class, new WinConditionFactory());
        }};
        testMoveFactory = new TestMoveFactory("elements.Test");
    }

    @Test
    void testCreateFactory() {}

    @Test
    void testBadPropertyFiles() {
        assertThrows(IllegalPropertyDefinitionException.class, () -> new TestMoveFactory("elements.BadTest1"));
        assertThrows(IllegalPropertyDefinitionException.class, () -> new TestMoveFactory("elements.BadTest2"));
    }

    @Test
    void testLoadProperties() {
        Collection<Property> props = testMoveFactory.getRequiredProperties();
        assertEquals(testProperties, props);
        testProperties.forEach(prop -> assertTrue(props.stream().anyMatch(prop::fullEquals)));
    }

    @Test
    void testCreateElement() {
        Move res = testMoveFactory.createElement("test", testMoveFactory.getRequiredProperties());
        assertTrue(res.checkName("test"));
        assertEquals(res.toRecord().properties(), testMoveFactory.getRequiredProperties());
    }

}