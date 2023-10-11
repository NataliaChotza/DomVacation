package dom.vacation.domvacation;


import Utils.DBUtils;
import Utils.Page;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class MainPageController {

    @FXML
    Button searchConnection_button;
    @FXML
    Label error_label;

    @FXML
    Label error_from;
    @FXML
    Label error_to;
    @FXML
    Label error_pass;
    @FXML
    Label error_dep;
    @FXML
    Label error_ret;
    @FXML
    CheckBox checkOneWay;
    @FXML
    Menu menuButton_currency;
    @FXML
    Label labelDeparture;
    @FXML
    Label labelReturn;
    @FXML
    DatePicker departureDate;
    @FXML
    DatePicker returnDate;
    @FXML
    TextField textFieldFrom;
    @FXML
    TextField textFieldTo;
    @FXML
    ChoiceBox menuBox;
    @FXML
     MenuItem item_adult;
    @FXML
    MenuItem item_child;
    @FXML
    MenuItem item_infant;
    @FXML

    Spinner adultsSpinner;

    @FXML

    Spinner childrenSpinner;
    @FXML

    Spinner infantsSpinner;
    @FXML
    Label label_error_from;
    @FXML
    Label label_error_to;
    @FXML
    ListView<Object> list_view_from = new ListView<>();
    @FXML
    ListView<Object> list_view_to = new ListView<>();
    @FXML
    static MenuButton items_spinner;
    ListView<Object> listView_destination = new ListView<>();

    @FXML
    Label currency_label = new Label();
    @FXML
    MenuItem logOut;

    LocalDate returnDateVal;
    LocalDate departureDateVal;
    Flight flight = new Flight();

    static Stage logOutStage;
    boolean is_from_field = false;
    TextField text_field_destination;
    Label label_error_destination = new Label();
    static Logger logger = Logger.getLogger("MainPageControllerLogger");

    @FXML
    public void logOut() {
        logger.info("Logged out from application");
        Parent root = null;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(SearchPageController.class.getResource("logOut.fxml")));
            setLogOutStage();
            Scene scene = new Scene(root);
            logOutStage.setScene(scene);
            logOutStage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    public void setCurrency() {
        logger.info("Set currency in application");
        ObservableList<MenuItem> menuItemList = FXCollections.observableArrayList();
        MenuItem pln = new MenuItem("PLN");
        MenuItem eur = new MenuItem("EUR");
        menuItemList.add(pln);
        menuItemList.add(eur);


        for (MenuItem menuItem : menuItemList) {
            if (0 == menuButton_currency.getItems().stream().filter(e -> e.getText().matches(menuItem.getText())).toList().size()) {
                menuItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        currency_label.setText(menuItem.getText());

                    }
                });
                menuButton_currency.getItems().addAll(menuItem);
            }
            //menuItem.setVisible(true);


        }
    }

    @FXML
    private void getDate() {
        logger.info("Get date ");

        if (checkOneWay.isSelected()) {
            // one datepicker
            System.out.println("checked one way");
            returnDate.setDisable(true);
        }
        if (!checkOneWay.isSelected() && returnDate.isDisabled()) {
            returnDate.setDisable(false);
        }
        returnDateVal = returnDate.getValue();
        departureDateVal = departureDate.getValue();
        System.out.println(" dep " + departureDateVal + " return: " + returnDateVal);
        flight.setDeparture_date(departureDateVal);
        flight.setReturn_date(returnDateVal);
    }

    public void putErrorStar(Label error_label_star) {
        error_label_star.setText("*");
        error_label_star.setTextFill(Color.RED);
        error_label_star.setVisible(true);
        error_label.setText("All boxes has to be filled");
        error_label.setTextFill(Color.RED);
        error_label.setVisible(true);
    }

    static Stage searchStage;

    public void setSearchStage(Stage searchStage) {
        MainPageController.searchStage = searchStage;
    }

    public static Stage getSearchStage() {
        return searchStage;
    }


    public static void setLogOutStage() {
        logOutStage = new Stage();
    }

    public static void closeLogOutStage() {
        logOutStage.close();
    }


    @FXML
    public void searchConnection() throws Exception {
        logger.info("Start search connection animation");
        flight.getFlightInfo();
        SearchPageController.startAnimation();
        Parent root = null;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(SearchPageController.class.getResource("searchPage.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LoginPageController.getMain_stage().close();
        Stage searchStage = new Stage();
        setSearchStage(searchStage);
        Scene searchScene = new Scene(root, 600, 420);
        getSearchStage().setScene(searchScene);
        getSearchStage().show();

        System.out.println("playing animation...");

    }

    public void setListView_destination(ListView<Object> listView_destination) {
        this.listView_destination = listView_destination;

    }

    public ListView<Object> getListView_destination() {
        return this.listView_destination;
    }

    public void setText_field_destination(TextField text_field_destination) {
        this.text_field_destination = text_field_destination;
    }

    public TextField getText_field_destination() {
        return this.text_field_destination;
    }

    public void setLabel_error_destination(Label label_error_destination) {
        this.label_error_destination = label_error_destination;
    }

    public Label getLabel_error_destination() {
        return this.label_error_destination;
    }

    public void getClickedListViewItem() {
        getListView_destination().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                String selected_item = getListView_destination().getSelectionModel().getSelectedItems().toString();

                getText_field_destination().setText(selected_item.split("\\[|\\]")[1]);
                getListView_destination().setVisible(false);


                System.out.println("This is selected value-> " + selected_item);


                if (textFieldFrom.getText().matches(textFieldTo.getText())) {
                    getLabel_error_destination().setText("You can not travel\nfrom same place you take off");
                    getLabel_error_destination().setPrefSize(200, 35);
                    getLabel_error_destination().setTextFill(Color.valueOf("#FF0000"));
                    getText_field_destination().setText(" ");
                }
            }
        });
    }

    public void getAirportName() {
        try {
            DBUtils.connectToDB(DBUtils.airportDataSQL);
            Map<String, String> airportData = DBUtils.getAirportData();
            getListView_destination().getItems().clear();

            String cityName = getText_field_destination().getText().toLowerCase();
            airportData.entrySet().stream()
                    .filter(e -> e.getValue().toLowerCase().startsWith(cityName))
                    .distinct()
                    .forEach(e -> getListView_destination().getItems().add(e.getKey()));


            if (!getListView_destination().isVisible())
                getListView_destination().setVisible(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void getAirportFrom() throws InterruptedException {
        logger.info("Get data about airport from");

        setListView_destination(list_view_from);
        setText_field_destination(textFieldFrom);
        is_from_field = true;

        setLabel_error_destination(label_error_from);
        getLabel_error_destination().setText("");

        getAirportName();

    }

    @FXML
    public void getAirportTo() {
        logger.info("Get data about airport to");

        setListView_destination(list_view_to);
        setText_field_destination(textFieldTo);

        setLabel_error_destination(label_error_to);
        getLabel_error_destination().setText("");

        getAirportName();

    }


    public void setSpinners() {
        adultsSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,5,0,1));
        int adultsNumber = Integer.parseInt(adultsSpinner.getValue().toString());
        childrenSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,adultsNumber+1,0,1));
        infantsSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,adultsNumber,0,1));
    }

}




