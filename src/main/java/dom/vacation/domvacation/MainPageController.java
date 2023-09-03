package dom.vacation.domvacation;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

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

    ListView<Object> listView_destination = new ListView<>();

    @FXML
    Label currency_label = new Label();
    @FXML
    MenuItem logOut;

    LocalDate returnDateVal;
    LocalDate departureDateVal;
    Flight flight = new Flight();

    static Stage logOutStage;
    @FXML
    public void logOut() {
        Parent root = null;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(SearchPageController.class.getResource("logOut.fxml")));
            setLogOutStage();
            Scene scene= new Scene(root);
            logOutStage.setScene(scene);
            logOutStage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    public void setCurrency() {

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


    public static void setLogOutStage(){
        logOutStage= new Stage();
    }
    public static void closeLogOutStage(){
         logOutStage.close();
    }


    @FXML
    public void searchConnection() throws Exception {
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



    List<Airport> resultSet_List = new ArrayList<>();
    String airport_code_from;
    String airport_code_to;
    String airport_code;
    boolean is_from_field = false;
    TextField text_field_destination;
    Label label_error_destination = new Label();

    public void setListView_destination(ListView<Object> listView_destination){
        this.listView_destination=listView_destination;

    }
    public ListView<Object> getListView_destination(){
        return this.listView_destination;
    }
    public void setText_field_destination(TextField text_field_destination){
        this.text_field_destination=text_field_destination;
    }
    public TextField getText_field_destination(){
        return this.text_field_destination;
    }
    public void setLabel_error_destination(Label label_error_destination){
        this.label_error_destination=label_error_destination;
    }
    public Label getLabel_error_destination(){
        return this.label_error_destination;
    }
    public void setAirport_code(String airport_code){
        this.airport_code=airport_code;
    }
    public String getAirport_code(){
        return this.airport_code;
    }

    public void getClickedListViewItem() {
        getListView_destination().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                String selected_item = getListView_destination().getSelectionModel().getSelectedItems().toString();

                getText_field_destination().setText(selected_item.split("\\[|\\]")[1]);
                getListView_destination().setVisible(false);


                System.out.println("This is selected value-> " + selected_item);

                if (is_from_field) {
                    System.out.println("from->: " + getAirport_code());
                    flight.setAirport_code_from(getAirport_code());
                } else {
                    System.out.println("to->: " + getAirport_code());
                    flight.setAirport_code_to(getAirport_code());
                }

                if (textFieldFrom.getText().matches(textFieldTo.getText())) {
                    getLabel_error_destination().setText("You can not travel\nfrom same place you take off");
                    getLabel_error_destination().setPrefSize(200, 35);
                    getLabel_error_destination().setTextFill(Color.valueOf("#FF0000"));
                    getText_field_destination().setText(" ");
                }
            }
        });
    }

    @FXML
    public void getAirportFrom() throws InterruptedException {

        textFieldFrom.setOnKeyTyped(new EventHandler<>() {

            @Override
            public void handle(KeyEvent keyEvent) {

                setListView_destination(list_view_from);
                setText_field_destination(textFieldFrom);
                is_from_field = true;
                setLabel_error_destination(label_error_from);
                setAirport_code(airport_code_from);
                getLabel_error_destination().setText("");
                //   connectToAPI(textFieldFrom.getText());

            }
        });
    }
    @FXML
    public void getAirportTo(){
        textFieldTo.setOnKeyTyped(new EventHandler<>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                setListView_destination(list_view_to);
                setText_field_destination(textFieldTo);
                setLabel_error_destination(label_error_to);
                is_from_field = false;
                setAirport_code(airport_code_to);
                getLabel_error_destination().setText("");
                //  connectToAPI(textFieldTo.getText());

            }
        });


    }
/*
    public void connectToAPI(String destination) {
        System.out.println("connecting to API..");
        System.out.println("user wrote->"+destination);
        getListView_destination().getItems().clear();
        resultSet_List.clear();
        try {

            response = Unirest.get("https://skyscanner44.p.rapidapi.com/autocomplete?query=" + destination)
                    .header("X-RapidAPI-Key", "dc45c53089msh5e3bd8147301bdep1be3b9jsnb6a4c8486b54")
                    .header("X-RapidAPI-Host", "skyscanner44.p.rapidapi.com")
                    .asJson();
            int iter = 0;
            while(!response.getBody().getArray().isNull(iter)) {
                setAirport_code(response.getBody().getArray().getJSONObject(iter).get("iata_code").toString());
                String country = response.getBody().getArray().getJSONObject(iter).get("country").toString();
                String city = response.getBody().getArray().getJSONObject(iter).get("city").toString();
                String name = response.getBody().getArray().getJSONObject(iter).get("name").toString();


                System.out.println("info->" + getAirport_code() + "" + country + "" + city + "" + name);
                Airport airObj = new Airport(city, name, country, getAirport_code());

                resultSet_List.add(airObj);
                iter++;
            }
        } catch (Exception ex) {
            if(ex.getMessage().contains("JSONException")){
                System.out.println(ex.getMessage());
            }
            throw new RuntimeException(ex);
        }
        for (Airport s : resultSet_List){
            getListView_destination().getItems().add(s.getAirportName());
            System.out.println(s.getAirportName());
        }
        if(!getListView_destination().isVisible())
            getListView_destination().setVisible(true);
    }


*/
}




