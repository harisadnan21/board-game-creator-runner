# Team OOGABOOGA Midpoint Demo

## Goals

### Strategy Games

In our project, we hope to create an application which allows users
to create strategy games in the following domain:
* the board is 2d and “checkerboard-like” and convex
* There are no more than 2 players, which can be AI or human
* Rules are deterministic
* Sequential & not “real-time” turns
* Perfect information

We intend two create two functionally separate application, a builder and a game engine. The builder
will be responsible for allowing users to create game configurations that specify the board, pieces,
rules, and conditions of a game. The engine is responsible for loading these configurations and
allowing users to play the games.

### Features

#### Builder

Users should be able to create a new game configuration, which involves:
- Creation of new Pieces
  - Images
  - Names
  - Behavior
- Creation of new Game Rules
  - Conditions
  - Actions
- Creation of win conditions
- Modification of the Board
  - Dimensions
  - Background
  - Piece Configuration

Users should be able to save and load game configurations so that they can be played on the engine.

#### Engine


### Sprint Priorities

#### Test Sprint
Creation of all APIs and basic implementations of the board, pieces, and view elements with a focus 
on writing tests for all code.

#### Sprint 1

Support for:
- Custom Pieces
- Custom Actions
- Custom Conditions
- Custom Rules
- Custom Board Configurations

In both the builder and the engine.

#### Sprint 2

Adding support for custom win conditions, as well as the basic implementation of extension features 
like artificial agents and networked games. Polishing the builder UI to make it more intuitive to 
users who wish to create games.

#### Complete Sprint

Finish implementation of extension features, as well as improving the UI for the builder and engine.
Adding more options for action, condition, and win condition creation.

### Project Management

Weekly team-wide meetings, with smaller meetings happening more often between the engine and builder
sub-teams. 

Use of Gitlab issues and code reviews to keep track of progress and future plans.

## Design

### Design and Framework Goals

#### Open

Actions, Conditions, and WinConditions are open for extension, meaning developers can create different
types of these game elements to add options for game creation to the users. The behavior of these
game elements is generic, allowing users to customize their parameters however they'd like.

#### Closed

The APIs for interaction between different game elements is closed. This simplifies the process of 
checking logic in the engine, as all actions, conditions, pieces, and boards have the same methods
for modification.

### Module Layout

#### Builder

`Controller`
- Defines the Controller that interfaces between the view and model
- Dependent on the view and the model

`View`
- Contains all UI elements of the builder
- `tab`
  - Holds a generic API for creating an arbitrary tab in the view, which can be responsible for any
  number of things, such as board modification, piece creation, action creation, etc.
  - If more game elements and options are desired, new tabs can be created by implementing the Tab
  interface
- Dependent on the controller

`Model`: Contains the modules and API for configuring a game and converting it to JSON
- `board`
  - Defines the Board
  - Different shapes and board behaviors can be defined by implementing the Board Interface
  - No Dependencies
- `element`
  - Defines the API for creating Game Elements (pieces, actions, rules, conditions)
  - New game elements can be created by implementing the Element Interface
  - Dependent on the property module
- `property`
  - Defines an API for creating and accessing arbitrary properties of game elements.
  - Properties are generic, immutable records that can be extended to different types.
  - No Dependencies

#### Engine

### APIs

#### API #1 - JSONSerializable
This interface describes methods for serializing and parsing GameElements in the Builder to their
JSON format.

```java
    /**
     * API For adapting Back-End Objects to their JSON Format
     *
     * @author Shaan Gondalia
     */
    public interface JSONSerializable<T> {
    
      /**
       * Converts the object into a String representing the object's JSON Format
       *
       * @return a String representation of the objects JSON Format
       */
      String toJSON() throws Exception;
    
      /**
       * Converts a JSON String into an object
       *
       * @param json the JSON string
       * @return an object of type T made from the JSON string
       */
      T fromJSON(String json);
    }
```

#### API #2

what service does it provide?
how does it provide for extension?
how does it support users (your team mates) to write readable, well design code?

### Use cases

#### Saving JSON Configuration
```java
  /**
   * Converts a Configuration into a String representing the model's JSON Format
   *
   * @return a String representation of the configuration's JSON Format
   */
  @Override
  public String toJSON() throws NullBoardException, ElementNotFoundException {
    checkBoardCreated();
    JSONObject obj = new JSONObject();
    // TODO: Remove magic values
    obj.put("pieces", elementsToJSONArray(PIECE));
    obj.put("board", board.toJSON());
    obj.put("rules", elementsToJSONArray(RULE));
    obj.put("conditions", elementsToJSONArray(CONDITION));
    obj.put("actions", elementsToJSONArray(ACTION));
    return obj.toString();
  }

// Converts all elements of a certain type to a JSONArray
private JSONArray elementsToJSONArray(String type) throws ElementNotFoundException {
    JSONArray arr = new JSONArray();
    if (!elements.containsKey(type)) {
        return arr;
    }
    for (GameElement element : elements.get(type).values()) {
        ElementRecord record = element.toRecord();
        arr.put(record.toJSON());
    }
    return arr;
}

/**
 * Converts an ElementRecord into a JSON String
 *
 * @return a JSON string
 */
@Override
public String toJSON() {
    JSONObject obj = new JSONObject();
    for (Property property : properties) {
      obj.put(property.name(), property.value());
    }
    obj.put(NAME, name);
    return obj.toString();
}

/**
 * Converts a Board into a String representing the board's JSON Format
 *
 * @return a String representation of the board's JSON Format
 */
@Override
public String toJSON() {
    JSONObject obj = new JSONObject();
    // TODO: Remove magic values
    obj.put("shape", "rectangle");
    obj.put("width", width);
    obj.put("height", height);
    obj.put("pieceConfiguration", pieceConfigToJSON());
    obj.put("activePlayer", 0);
    obj.put("background", "checkers");
    obj.put("selectionsRequired", true);
    return obj.toString();
}
```

### Alternative Design & Tradeoffs

#### Builder View

Alternate Design - Game Creation happens in a series of steps. Users cannot proceed to the next step
until they are finished with the current step
Current Design - Users can edit any GameElements at any time, it is not sequential.

Trade-Offs
- A sequential system simplifies error-checking and makes sure that the user cannot break the game
configuration in unexpected ways.
- A non-sequential system allows more flexibility and creates a UI that is intuitive for 
users. Users may become frustrated if they constantly have to go back to previous steps if they 
realized they made a mistake.

#### Engine Board

Current Design - Representing Cells in a 2d array
Alternate Design - Representing Cells in a Map instead of a 2d array

Trade-Offs
- Maps allow abstraction to different board shapes
- A 2d array is less flexible but simpler to implement
- An initial assumption of the plan was that all games would have rectangular boards