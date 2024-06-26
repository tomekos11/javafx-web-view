package ts.demo1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        Scene scene = new Scene(fxmlLoader.load(), screenBounds.getWidth(), screenBounds.getHeight() - 30);
        stage.setTitle("JavaPro");
        stage.setScene(scene);

        stage.setOnCloseRequest(event -> {
            // Dodaj tutaj kod zamknięcia połączeń, jeśli jest potrzebny
            System.out.println("Application is closing...");
        });

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}