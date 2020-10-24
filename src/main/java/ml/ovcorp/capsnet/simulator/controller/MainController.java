package ml.ovcorp.capsnet.simulator.controller;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import ml.ovcorp.capsnet.simulator.model.CircleObject;
import ml.ovcorp.capsnet.simulator.model.DrawnObject;

import java.util.HashSet;
import java.util.Set;

public class MainController {

    @FXML
    private ToggleGroup instrumentToggleGroup;
    @FXML
    private Button infoOperationButton;
    @FXML
    private Button infoHoverDrawnObjectButton;
    @FXML
    private Pane canvasPane;
    private Canvas canvas;

    private final Set<DrawnObject> drawnObjectHashSet = new HashSet<>();
    private DrawnObject selectedDrawnObject;

    @FXML
    public void initialize() {

        buildInstrumentToggleGroup();
        buildCanvas();

    }

    public void buildInstrumentToggleGroup() {
        instrumentToggleGroup.selectedToggleProperty().addListener((ov, old_toggle, new_toggle) -> {

            if (instrumentToggleGroup.getSelectedToggle() != null) {
                infoOperationButton.setText("Операция: построение '" + ((ToggleButton) instrumentToggleGroup.getSelectedToggle()).getText() + "'");
            } else {
                infoOperationButton.setText("Ожидание выбора операции");
            }

        });
    }

    public void buildCanvas() {
        canvas = new Canvas();
        canvasPane.getChildren().add(canvas);

        canvas.widthProperty().bind(canvasPane.widthProperty());
        canvas.heightProperty().bind(canvasPane.heightProperty());


        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            double x = event.getX();
            double y = event.getY();

            int radius = 25;
            DrawnObject resultCircleObject = getDrawnObjectByCoordinates(x, y);

            if (resultCircleObject == null) {
                CircleObject circleObject = new CircleObject(x - 25, y - 25, Color.STEELBLUE, radius);
                drawnObjectHashSet.add(circleObject);
                selectedDrawnObject = circleObject;
            } else {
                if (event.getButton() == MouseButton.SECONDARY) {
                    if (resultCircleObject instanceof CircleObject) {
                        CircleObject circleObject = (CircleObject) resultCircleObject;
                        drawnObjectHashSet.remove(circleObject);
                    }
                }
            }
            redraw();
        });

        canvas.addEventHandler(MouseEvent.MOUSE_MOVED, event -> {
            double x = event.getX();
            double y = event.getY();

            highlight(x, y);
        });
        //        // redraw when resized
        canvas.widthProperty().addListener(event -> redraw());
        canvas.heightProperty().addListener(event -> redraw());
    }

    private DrawnObject getDrawnObjectByCoordinates(double x, double y) {
        for (DrawnObject drawnObject : drawnObjectHashSet) {
            if (drawnObject.coordinatesBelong(x, y)) {
                return drawnObject;
            }
        }
        return null;
    }

    private void highlight(double x, double y) {
        DrawnObject drawnObject = getDrawnObjectByCoordinates(x, y);

        if (drawnObject != null && !drawnObject.equals(selectedDrawnObject)) {
            if (drawnObject instanceof CircleObject) {
                selectedDrawnObject = drawnObject;
            }
        } else if (drawnObject == null) {
            selectedDrawnObject = null;
        }
        redraw();
    }

    private void redraw() {
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (DrawnObject drawnObject : drawnObjectHashSet) {
            if (drawnObject instanceof CircleObject) {
                CircleObject circleObject = (CircleObject) drawnObject;

                graphicsContext.setFill(Color.STEELBLUE);
                graphicsContext.fillOval(circleObject.getX(), circleObject.getY(), circleObject.getWidth(), circleObject.getHeight());

                if (drawnObject.equals(selectedDrawnObject)) {
                    graphicsContext.setStroke(Color.BLACK);
                    graphicsContext.setLineDashes(2d);
                    graphicsContext.strokeRect(circleObject.getX(), circleObject.getY(), circleObject.getWidth(), circleObject.getHeight());
                }
            }
        }
    }
}
