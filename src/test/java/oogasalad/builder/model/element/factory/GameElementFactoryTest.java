package oogasalad.builder.model.element.factory;

import oogasalad.builder.controller.Property;
import oogasalad.builder.model.element.*;
import oogasalad.builder.model.exception.IllegalPropertyDefinitionException;
import oogasalad.builder.model.exception.MissingRequiredPropertyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

class GameElementFactoryTest {
    private Map<Class<? extends GameElement>, GameElementFactory> factories;
    private TestRuleFactory testRuleFactory;
    private Collection<Property> testProperties = Set.of(new Property(String.class, "thingy", "hello there"),
            new Property(Integer.class, "name", "123"));

    private class TestRuleFactory extends GameElementFactory<Rule> {
        public TestRuleFactory(String path) {
            super(path);
        }

        @Override
        public Rule createElement(String name, Collection<Property> properties) {
            return new Rule(name, properties);
        }
    }

    @BeforeEach
    void setup() {
        factories = new HashMap<>() {{
            put(Piece.class, new PieceFactory());
            put(Rule.class, new RuleFactory());
            put(WinCondition.class, new WinConditionFactory());
        }};
        testRuleFactory = new TestRuleFactory("elements.Test");
    }

    @Test
    void testCreateFactory() {}

    @Test
    void testBadPropertyFiles() {
        assertThrows(IllegalPropertyDefinitionException.class, () -> new TestRuleFactory("elements.BadTest1"));
        assertThrows(IllegalPropertyDefinitionException.class, () -> new TestRuleFactory("elements.BadTest2"));
    }

    @Test
    void testLoadProperties() {
        Collection<Property> props = testRuleFactory.getRequiredProperties();
        assertEquals(testProperties, props);
        testProperties.forEach(prop -> assertTrue(props.stream().anyMatch(prop::fullEquals)));
    }

    @Test
    void testCreateElement() {
        Rule res = testRuleFactory.createElement("test", testRuleFactory.getRequiredProperties());
        assertTrue(res.checkName("test"));
        assertEquals(res.toRecord().properties(), testRuleFactory.getRequiredProperties());
        factories.forEach((clazz, factory) -> {
            try {
                assertEquals(factory.createElement("", factory.getRequiredProperties()).getClass(), clazz);
            } catch (MissingRequiredPropertyException e) {
                e.printStackTrace();
            }
        });
    }

}