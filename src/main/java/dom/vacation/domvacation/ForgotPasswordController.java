package dom.vacation.domvacation;


import Utils.DBUtils;
import Utils.Page;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ForgotPasswordController{

    @FXML
    public Button go_back_button;
    @FXML
    Button resetPasswordButton;
    @FXML
    TextField emailField;
    @FXML
    Label errorLabel;


    @FXML
    public void goBackToLoginPage(){
        Page.goBackToLoginPage();
    }
    @FXML
    public void resetPassword(){
        //reset passsword
        errorLabel.setVisible(false);
        String email = emailField.getText();

        try {
            DBUtils.connectToDB(DBUtils.clientTableSQL);
            if (DBUtils.userExist(email)){
                Parent root = FXMLLoader.load(getClass().getResource("emailSent.fxml"));
                Scene scene = new Scene(root);
                Stage emailSentStage = new Stage();
                emailSentStage.setScene(scene);
                emailSentStage.setResizable(false);
                emailSentStage.show();
            }else{
                errorLabel.setVisible(true);
                errorLabel.setTextFill(Color.RED);
                errorLabel.setText("The user doesn't exist");
            }
        }catch (SQLException | IOException ex){
            ex.printStackTrace();
        }
        new EmailSentController(email);

    }



}
