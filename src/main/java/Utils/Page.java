package Utils;

import dom.vacation.domvacation.LoginPage;
import dom.vacation.domvacation.LoginPageController;

public interface Page {


     static void goBackToLoginPage(){
        LoginPage.getStage().show();

        LoginPageController.getRegistration_stage().close();
    }

}
