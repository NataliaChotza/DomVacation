package dom.vacation.domvacation;

import Utils.DBUtils;
import Utils.Page;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.sql.SQLException;


public class RegisterNewUserController {

    @FXML
    Button go_back_button;

    @FXML
    TextField emailBox;
    @FXML
    Button signUpButton;
    @FXML
    Label error_label;
    @FXML
    TextField passwordTextField;
    @FXML
    TextField repeatPasswordTextField;
    @FXML
    TextField logintextField;

    String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

    @FXML
    public void registerNewUser(){
        error_label.setVisible(false);
        try {
            if (passwordTextField.getText().matches(repeatPasswordTextField.getText())) {
                String cEmail = (emailBox.getText());
                DBUtils.connectToDB(DBUtils.clientTableSQL);
                if (!DBUtils.userExistByEmail(cEmail)) {
                    if (!isEmailCorrect(cEmail)) {
                        error_label.setText("Email should be format xxxxx@xxxxx.xxx");
                        error_label.setTextFill(Color.RED);
                        error_label.setVisible(true);
                    } else {
                        DBUtils.addUser(emailBox.getText(), passwordTextField.getText(), logintextField.getText());
                        Page.goBackToLoginPage();
                    }
                } else {
                    error_label.setText("User exist with this email");
                    error_label.setTextFill(Color.RED);
                    error_label.setVisible(true);
                }

            } else {
                error_label.setText("Given passwords are not the same");
                error_label.setTextFill(Color.RED);
                error_label.setVisible(true);
            }
            DBUtils.closeConnection();
        }catch (SQLException ex){
            ex.printStackTrace();
        }

    }
    public boolean isEmailCorrect(String email){
        return email.matches(emailRegex);


    }

    @FXML
    public void goBackToLoginPage(){
        Page.goBackToLoginPage();
    }

}
