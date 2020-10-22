/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ml.ovcorp.capsnet.simulator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class App extends Application {
    public String getGreeting() {
        return "Hello world.";
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = App.class.getResource("/main.fxml");
        Parent root = FXMLLoader.load(url);

        Scene scene = new Scene(root, 300, 300);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Software Simulator CapsNet");

        primaryStage.show();
    }
}