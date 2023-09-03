package dom.vacation.domvacation;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class EmailSentController {
    @FXML
    Label emailLabel;

    public EmailSentController(String password){
        emailLabel.setTextFill(Color.BLACK);
        emailLabel.setText(password);
    }

}
