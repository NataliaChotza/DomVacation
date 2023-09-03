package dom.vacation.domvacation;
import java.io.IOException;
import java.util.Objects;

import Utils.DBUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.sql.* ;
public class LoginPageController {

    @FXML
    TextField loginTextField;
    @FXML
    Button loginButton;
    @FXML
    PasswordField passwordField;
    @FXML
    Label wrongDataLabel;
    @FXML
    Label fpLabel;
    @FXML
    Label register_label;

    static Stage registration_stage;
    static Stage main_stage;
    static Stage forgotPassword_stage;

    @FXML
    private void loginToApp() {
        wrongDataLabel.setVisible(false);
        String email = loginTextField.getText();
        String password = passwordField.getText();
        try {
            DBUtils.connectToDB(DBUtils.clientTableSQL);
            if(DBUtils.userExistByEmail(email)) {
                wrongDataLabel.setVisible(false);
                if (DBUtils.correctLogInData(email, password)) {
                    Parent root = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
                    Scene scene = new Scene(root);
                    main_stage = new Stage();
                    setMain_stage(main_stage);
                    getMain_stage().setResizable(false);
                    getMain_stage().setScene(scene);
                    getMain_stage().show();
                    LoginPage.getStage().close();

                } else {
                    wrongDataLabel.setText("Wrong data");
                    wrongDataLabel.setTextFill(Color.RED);
                    wrongDataLabel.setVisible(true);
                }
            }else{
                wrongDataLabel.setText("The user doesn't exist");
                wrongDataLabel.setTextFill(Color.RED);
                wrongDataLabel.setVisible(true);
            }
            DBUtils.closeConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void forgotPassword(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("fp.fxml"));
            Scene scene = new Scene(root);
            forgotPassword_stage = new Stage();
            setForgotPassword_stage(forgotPassword_stage);
            getForgotPassword_stage().setResizable(false);
            getForgotPassword_stage().setScene(scene);
            getForgotPassword_stage().show();
            LoginPage.getStage().close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    public void register_new_user(){
        Parent root = null;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("registration.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        registration_stage = new Stage();
        registration_stage.setResizable(false);
        registration_stage.setScene(scene);
        registration_stage.show();
        LoginPage.getStage().close();


    }
    public void setMain_stage(Stage main_stage){
        LoginPageController.main_stage =main_stage;
    }
    public static Stage getMain_stage(){
        return main_stage;
    }
    public static Stage getRegistration_stage(){
        return registration_stage;
    }
    public void setForgotPassword_stage(Stage forgotPasswordStage){
        LoginPageController.forgotPassword_stage =forgotPasswordStage;
    }
    public static Stage getForgotPassword_stage(){
        return forgotPassword_stage;
    }



}