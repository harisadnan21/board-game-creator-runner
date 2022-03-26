// User clicks on the "Create New Piece" button in PiecesTab
// An event is triggered, calling a private callback method (or maybe lambda) in BuilderView
// The private methods in controller that the view is calling were passed in as functional interfaces
//In BuilderView (type is set to "piece"):
private void newPieceCallback(String type){
    Collection<Property> defaultProperties = controller.getElementProperties(type);
    ElementRecord record = new ElementRecord(type, "default", defaultProperties);
    controller.update(record);
}

//In BuilderController
private Collection<Property> getElementProperties(String type) {
    return model.getElementProperties(type);
}

// In BuilderModel
private Collection<Property> getElementProperties(String type) {
    return factories.get(type).getProperties();
}

// In BuilderController again
private void update(ElementRecord element) {
    model.putGameElement(element.type(), element.name(), element.properties());
    view.putGameElement(element);
}

// In BuilderModel again
public void putGameElement(String type, String name, Collection<Property> properties) {
    gameConfiguration.putGameElement(type, factories.get(type).createElement(name, properties));
}

// In GameConfiguration
public void putGameElement(String type, GameElement element) {
    gameElements.get(type).put(element.getName(), element);
}

// In BuilderView again
public void putGameElement(ElementRecord element) {
    tabs.get(element.type).putGameElement(element);
}

// In PiecesTab
public void putGameElement(ElementRecord element) {
    gameElementList.putGameElement(element);
    // make this the selected piece by default
    propertyEditor.setProperties(element.properties);
}