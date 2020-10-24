module capsnet.software.simulator.main {
    exports ml.ovcorp.capsnet.simulator;
    opens ml.ovcorp.capsnet.simulator to javafx.graphics;

    exports ml.ovcorp.capsnet.simulator.controller;
    opens ml.ovcorp.capsnet.simulator.controller to javafx.fxml;

    requires javafx.controls;
    requires javafx.fxml;
}