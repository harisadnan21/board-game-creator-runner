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
`Controller`
- Initializes the model and serves as the link between the model and the view
- Dependent on the model
`Model`
- `Action`
  - Defines api for the different actions that can execute in the game.
  - Includes moving, placing, and removing pieces
- `Board Condition`
  - API that defines board state conditions.
  - Board Condition is and can be extended for new conditions


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

### Alternative Design & Tradeoffs



## Current Functionality

### Builder Demo

### Engine Demo

### Example Data Files

### Tests