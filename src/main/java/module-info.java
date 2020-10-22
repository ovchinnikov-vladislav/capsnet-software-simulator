module capsnet.software.simulator.main {
    exports ml.ovcorp.capsnet.simulator;
    opens ml.ovcorp.capsnet.simulator to javafx.graphics;

    requires javafx.controls;
    requires javafx.fxml;
}