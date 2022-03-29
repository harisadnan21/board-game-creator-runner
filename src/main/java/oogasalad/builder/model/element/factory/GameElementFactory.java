package oogasalad.builder.model.element.factory;


import oogasalad.builder.controller.ExceptionResourcesSingleton;
import oogasalad.builder.controller.Property;
import oogasalad.builder.model.element.GameElement;

import java.util.ResourceBundle;
import java.util.Set;

public abstract class GameElementFactory<T extends GameElement> {
    private static final int PROPERTY_PARTS = 3;

    private ResourceBundle propertiesResources;

    private Set<Property> properties;

    public GameElementFactory(String propertiesPath) {
        propertiesResources = ResourceBundle.getBundle(propertiesPath);
    }

    private void loadProperties() {
        propertiesResources.getKeys().asIterator()
                .forEachRemaining(key -> {
                    String[] propertyParts = propertiesResources.getString(key).split("\\|");
                    if(propertyParts.length != PROPERTY_PARTS) {
                        throw new IllegalPropertyDefinitionException(ExceptionResourcesSingleton.getInstance().getString("BadPropertyPartLength", PROPERTY_PARTS));
                    }
                    try {
                        properties.add(new Property(Class.forName(propertyParts[0]), propertyParts[1], propertyParts[2]));
                    } catch(ClassNotFoundException e) {
                        throw new IllegalPropertyDefinitionException(ExceptionResourcesSingleton.getInstance().getString("BadPropertyClass", propertyParts[0]));
                    }
                });
    }

    /**
     * @param properties 
     * @return
     */
    public abstract T createElement(String name, Set<Property> properties);

    /**
     * @return
     */
    public Set<Property> getProperties() {
        return Set.copyOf(properties);
    }

}