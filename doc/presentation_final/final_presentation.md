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

* Revisit the design from the original plan and compare it to your current version (as usual, focus on the behavior and communication between modules, not implementation details).
* Revisit the design's goals: is it as flexible/open as you expected it to be and how have you closed the core parts of the code in a data driven way?

* Describe two APIs in detail (one from the first presentation and a new one):
  * API One:
    * Show the public methods for the API
    * How does it provide a service that is open for extension to support easily adding new features?
    * How does it support users (your team mates) to write readable, well design code?
    * How has it changed during the Sprints (if at all)?

  * API Two: Alex - API used inside AI - Interface of Selects & State Evaluator
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

* Present what your team and, you personally, learned from managing this project:
* contrast the completed project with where you planned it to be in your initial Wireframe and the initial planned priorities and Sprints with the reality of when things were implemented
  Individually, share one thing each person learned from using the Agile/Scrum process to manage the project
* show a timeline of at least four significant events (not including the Sprint deadlines) and how communication was handled for each (i.e., how each person was involved or learned about it later)
  Individually, share one thing each person learned from trying to manage a large project yourselves
* describe specific things the team actively worked to improve on during the project and one thing that could still be improved
  Individually, share one thing each person learned about creating a positive team culture
* revisit your Team Contract to assess what parts of the contract are still useful and what parts need to be updated (or if something new needs to be added)
  Individually, share one thing each person learned about how to communicate and solve problems collectively, especially ways to handle negative team situations
