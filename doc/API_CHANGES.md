## OOGASalad API Changes
### Team OOGABOOG
### Names


### Changes Made

#### BuilderModel

* Method added: makeBoard
  * We wanted to make the board object internal to the model, so we had to create an external API for making a board in the model
  * Minor, the controller now calls makeBoard instead of initializing a Board() itself

* Method added: findElementInfo
  * Needed a way to display all element info to the view without the view keeping track of this information itself
  * Major, the view no longer needed to keep track of previous properties. Instead, this is handled by the model and the view simply retrieves the data
  * Better, the view no longer needs to store any information about game elements that were already created.

* Method added: getElementNames
  * Needed a method to display all element names in the tabs
  * Minor, only added functionality
  * Better, because the options for retrieving data from the model were quite limited before

* Method added: clearBoardCell, clearCellBackground, colorCellBackground, findCellBackground
  * Originally, we didn't think we were going to store information about the background of cells in the model. Later, we realized that we need to store this in the back-end in order for it to be serialized. As a result, these methods were added to allow the controller to modify the configuration of cell colors in the backend
  * Minor, only added functionality
  * Better, because otherwise the view would have to take part in JSON Serialization, which was a design decision that we decided against

* Method added: getRequiredProperties
  * Needed to return the required properties of an element
  * Minor, only added functionality
  * Better, because the model is responsible for storing and loading properties from configuration files and properties files

* Method added: copyFiles
  * Late in the project we realized that we would need some way to copy files from the user's file system to a game configuration directory so that the files could be saved and configurations could be shared with other users.
  * Minor, only added functionality
  * Better, because we wanted flexibility on where users could create new game configurations.

#### Board (builder)

* Method changed: placePiece takes an int id instead of a String name
  * We wanted the board data structure to keep track of piece ids instead of piece names for serialization purposes. We figured that pieces should not be referenced by name, but rather by id in the board
  * Minor, this API is internal to the model and simply requires a mapping from piece name to id


* Methods added: clearCell, clearCellBackground, colorCellBackground, findCellBackground
  * Originally, we didn't think we were going to store information about the background of cells in the model. Later, we realized that we need to store this in the back-end in order for it to be serialized. As a result, these methods were added to allow the controller to modify the configuration of cell colors in the backend
  * Minor, only added functionality. The board now internally stores hexadecimal encodings of the cell colors so that it can serialize them.
  * Better, because otherwise the view would have to take part in JSON Serialization, which was a design decision that we decided against

  
#### GameElement (builder)

* Method changed: getProperties() and putProperty() removed and replaced with toRecord()
  * We wanted game elements to be immutable objects, and we didn't want to allow the modification of properties for game elements
  * Major, properties can only be set for a game element within its constructor (or factory).
  * Better, we reduced the visibility of data in our game elements. Data is now available for reading but not writing.


* Method added: checkName
  * Convenience method for checking if the name of a game element matches another name
  * Minor, this is only a convenience method to reduce code repetition
  * Better, reduced code repetition while keeping implementation details hidden

#### Property (builder)

* Method changed: type() was switched to form()
  * Properties don't have a "type", they rather have a form that informs how they are chosen in the front end
  * Minor, the type of the property was not used anywhere
  * Better, removed unnecessary information
  

#### GameElementFactory
* Method renamed: getProperties() became getRequiredProperties()
  * Wanted a clearer name for what this method did
  * Very minor, just renamed the method
  * Better, provided a clearer name

#### JSONParseable/JSONSerializable
* GameLoader and GameSaver were replaced with JSONParseable and JSONSerializable interfaces
  * We wanted elements to be "parseable" and "serializable" instead of having one parser/serializer that would handle all elements of parsing
  * Major, added toJSON and fromJSON methods to many existing APIs
  * Better, adheres to Single Responsibility Principle. Classes should know how to save/load themselves to JSON format.