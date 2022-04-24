open module oogasalad_app {

    // list all imported class packages since they are dependencies
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.base;
    requires javafx.media;
    requires javafx.web;
    requires org.json;
    requires java.datatransfer;
    requires java.desktop;
    requires io.vavr;
    requires org.jooq.jool;
    //requires com.google.gson;

    requires org.apache.logging.log4j;
    requires com.github.benmanes.caffeine;
    requires lombok;

    // allow other classes to access listed packages in your project

    exports oogasalad;
    exports oogasalad.engine.view;
    exports oogasalad.engine.view.setup.dashboard;
    exports oogasalad.engine.view.ControlPanel;
  exports oogasalad.engine.view.setup;
    exports oogasalad.engine.view.game;
}
