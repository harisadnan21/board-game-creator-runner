# OOGABOOGA Final Presentation

## Demo

### Game Player

#### Example Games

* Tic-Tac-Toe
  * Show winning and losing
* Checkers
  * Save and restart from that point
  * Play Tic Tac Toe at the same time
  * Alex: AI Player - show the available options/difficulties and demonstrate AI making moves in Checkers
* Minesweeper
  * Different Color Themes and Languages
* Wordle
  * Display High Scores
  
### Authoring Environment
* Load in checkers and make 3 edits
  * Remove Last Row of Pieces
  * Add Capturing of own pieces
  * Add win condition for reaching other side of board
* Create Simple Piece Placement Game
* Data Files
  * Essential - Internal properties files describe the available Game Elements
  * User Created - Game Configuration Folder
    * JSON Game configuration
    * Resources Folder
    * Game Icons

### Tests
* Run all tests, verifying that they pass
* Describe Tests
  * Alex: Backend Unit Tests - Board/AI
  * Frontend TestFX - TODO
  * Integration Tests - Game Configuration Saving/Loading

### Additional Features


## Design

### High Level Design

#### Engine

**TODO**


#### Builder
The builder implements an MVC architecture. The view dispatches callbacks to the controller, which
calls the model to update the game configuration accordingly.

###### Model

The model is composed of a few major APIs that dictate how information is stored in the back-end of
the builder. The `BuilderModel` is a single API that provides access methods for modifying all data in
the model (so that the controller has a single external API to call).

The `Property` API describes how properties are stored in the builder. A Property is just some
information about a game element (such as the name of a piece or rule). Properties are immutable,
but were opted to be a class instead of a record because we felt that an `AbstractProperty` class
would reduce code repetition.

The `Element` API represents a single game element in the configuration (i.e. an entire piece or
rule). Elements are essentially collections of properties. The `ElementFactory` API has methods for
creating elements, including retrieving the required properties for certain types of elements. The
`FactoryProvider` implements the [Abstract Factory Pattern](https://www.tutorialspoint.com/design_pattern/abstract_factory_pattern.htm),
providing methods for creating elements without the need for concrete declarations of ElementFactories.

The `Board` API stores information about the board. This includes size, shape, piece configuration,
and cell colors. While the cell colors are only needed in the view for display purposes, they must
be stored in the model in order to be serialized to JSON.

The `JSONParseable` and `JSONSerializable` interfaces each provide a single method that determines how
a class is parsed or serialized (reading or loading from JSON). Using this interface standardized
the process of parsing and serialization, and meant that there did not need to be a single
Parser/Serializer that held all information about saving and loading a game configuration from JSON.
We felt that this design better adhered to the Single Responsibility Principle and active classes.

###### View

The view is composed of several basic JavaFX view classes as well as several abstractions. The view
is split into Tabs, which are each responsible for the creation of a single game element. These tabs
all implement the `BasicTab`, which is the internal API for creating a tab in the builder.

Each tab has a `GameElementList` (lists existing game elements) and PropertySelectors.
`PropertySelector` is an API that defines general methods for allowing users to set the values of
certain properties. For instance, an `IntegerSelector` prompts users to select integers and a
`BooleanSelector` prompts users to select a boolean value.

The view communicates with the controller by dispatching `Callback`s, which are handled by the
controller using a Listener based system. The view obtains information about the existing game
configuration from the model using these Callbacks. The view does not store any information about
the game elements other than their names, and retrieves the necessary information if users wish
to edit existing elements.


### Design Goals: Flexibility 

The design is as flexible as we wanted it to be.

In the engine, the system of Actions, Conditions, and WinDecisions
follows a clear inheritance hierarchy with public APIs that define how these elements interface with
each other. The creation of new game elements is very easy with this system, meaning it is open to 
extension.

Likewise in the builder, adding new game elements and types of game elements is as simple as 
implementing the corresponding API. These APIs are closed for modification, as their method 
signature should not change. Concrete classes are not referenced anywhere in the code, adding 
flexibility.

### APIs

#### JSONSerializable and JSONParseable

This API (made of two interfaces) describes methods for serializing and parsing GameElements in the 
Builder to their JSON format. We decided to split the Interface into two, because certain elements
are serializable but not parseable.

```java
/**
 * API For adapting Back-End Objects to their JSON Format
 *
 * @author Shaan Gondalia
 */
public interface JSONSerializable {

  /**
   * Converts the object into a String representing the object's JSON Format
   *
   * @return a String representation of the objects JSON Format
   */
  String toJSON();

}

/**
 * API for converting a json string to an Object
 *
 * @author Shaan Gondalia
 */
public interface JSONParseable<T> {

  /**
   * Converts a json string to an object of type T
   *
   * @param json a json string
   * @return an object of type T built from the json String
   */
  T fromJSON(String json) throws Exception;

}
```

* How does it provide a service that is open for extension to support easily adding new features?
  * The interfaces are generic, meaning they can be applied to any class. Their method signatures 
  are primitive types and generics, so they are not implementation specfic.
* How does it support users (your team mates) to write readable, well design code?
  * Instead of having a single Parsing Object responsible for knowing how to parse/serialize each
  class in the game, we can define how classes are parsed and serialized in the class itself. These
  classes are now more active.
* How has it changed during the Sprints (if at all)?
  * It was split into two, originally it was just JSONSerializable which held the toJSON and fromJSON
  methods.

#### Alex: AI - Interface of Selects & State Evaluator
* Show the public methods for the API
* How does it provide a service that is open for extension to support easily adding new features?
* How does it support users (your team mates) to write readable, well design code?
* How has it changed during the Sprints (if at all)?

  * Show two Use Cases implemented in Java code in detail that show off how to use each of the APIs described above
    * API One: ALex
      * Use Case 1: AI Player using Selects
      * Use Case 2: Selects using StateEvaluator
* Describe two designs
  * One that has remained stable during the project
  * One that has changed significantly based on your deeper understanding of the project: how were those changes discussed and what trade-offs ultimately led to the changes
    * Alex: discuss change to Board, going from mutating one Board to having persistent Boards

## Team


### What we learned from the Agile Process


### What we learned from trying to manage such a large project


### What we learned about creating a positive team culture


### What we learned about communication and collective problem solving

* contrast the completed project with where you planned it to be in your initial Wireframe and the initial planned priorities and Sprints with the reality of when things were implemented
  Individually, share one thing each person learned from using the Agile/Scrum process to manage the project
* show a timeline of at least four significant events (not including the Sprint deadlines) and how communication was handled for each (i.e., how each person was involved or learned about it later)
  Individually, share one thing each person learned from trying to manage a large project yourselves
* describe specific things the team actively worked to improve on during the project and one thing that could still be improved
  Individually, share one thing each person learned about creating a positive team culture
* revisit your Team Contract to assess what parts of the contract are still useful and what parts need to be updated (or if something new needs to be added)
  Individually, share one thing each person learned about how to communicate and solve problems collectively, especially ways to handle negative team situations
