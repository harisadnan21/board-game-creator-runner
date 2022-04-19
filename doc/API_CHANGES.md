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




#### API #2

* Method changed:

    * Why was the change made?

    * Major or Minor (how much they affected your team mate's code)

    * Better or Worse (and why)


* Method changed:

    * Why was the change made?

    * Major or Minor (how much they affected your team mate's code)

    * Better or Worse (and why)


#### API #3

* Method changed:

    * Why was the change made?

    * Major or Minor (how much they affected your team mate's code)

    * Better or Worse (and why)


* Method changed:

    * Why was the change made?

    * Major or Minor (how much they affected your team mate's code)

    * Better or Worse (and why)


#### API #4

* Method changed:

    * Why was the change made?

    * Major or Minor (how much they affected your team mate's code)

    * Better or Worse (and why)


* Method changed:

    * Why was the change made?

    * Major or Minor (how much they affected your team mate's code)

    * Better or Worse (and why)

