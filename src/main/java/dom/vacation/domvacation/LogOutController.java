package dom.vacation.domvacation;


import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class LogOutController {

    @FXML
    Button cancelButton;
    @FXML
    Button logOutButton;

    @FXML
    public void cancelPressed(){
        MainPageController.closeLogOutStage();


    }
    @FXML
    public void logOutPressed(){
        MainPageController.closeLogOutStage();
        LoginPage.getStage().show();
        LoginPageController.getMain_stage().close();
    }



}