package ts.demo1;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private WebView webView;
    private WebEngine webEngine;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        webEngine = webView.getEngine();
        webEngine.setJavaScriptEnabled(true);

        webEngine.setOnAlert((WebEvent<String> event) -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText(null);
            alert.setContentText(event.getData());
            alert.showAndWait();
        });

        // Obsługa błędów
        webEngine.setOnError(event -> {
            System.err.println("Error: " + event.getMessage());
        });

        webEngine.getLoadWorker().exceptionProperty().addListener((obs, oldExc, newExc) -> {
            if (newExc != null) {
                newExc.printStackTrace();
            }
        });

        // Śledzenie statusu ładowania
        webEngine.getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
            switch (newState) {
                case SUCCEEDED:
                    System.out.println("Page loaded successfully");
                    break;
                case FAILED:
                    System.out.println("Page load failed");
                    break;
                case CANCELLED:
                    System.out.println("Page load cancelled");
                    break;
            }
        });

        webEngine.load("http://127.0.0.1:9000/");
    }
}