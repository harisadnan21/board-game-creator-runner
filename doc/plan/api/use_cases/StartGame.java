/**
 * API use Case detailing the classes and methods involved in starting a game in the engine
 *
 */

//On starting the game engine, users will first interact with Dashbord
Dashboard dashboard = new Dashboard();
Dashboard.playGame();

//Controller Starts game select based on available Game folders
Controller controller = new Controller();
controller.startEngine();
ViewManager view = new ViewManager(controller.getAvailablegames());

//Selected Game determines Game that is rendered to screen
view.createGameView(Path gamePath)