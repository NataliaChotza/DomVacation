package dom.vacation.domvacation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.scene.Parent;

public class LoginPage extends Application {

    static Stage app_stage;

    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load((getClass().getResource("loginPage.fxml")));
        Scene scene = new Scene(root, 465, 443);
        stage.setScene(scene);
        setStage(stage);
        stage.setResizable(false);
        stage.show();

    }
    public void setStage(Stage stage){
        app_stage=stage;
    }

    public static Stage getStage() {
        return app_stage;
    }

    public static void main(String[] args) {
        launch();
    }
}
