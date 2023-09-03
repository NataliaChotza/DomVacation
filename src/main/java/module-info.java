module dom.vacation.domvacation {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens dom.vacation.domvacation to javafx.fxml;
    exports dom.vacation.domvacation;
}