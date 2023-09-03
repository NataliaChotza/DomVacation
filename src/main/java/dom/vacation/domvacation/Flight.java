package dom.vacation.domvacation;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Flight {
    String x_rapidapi_host_flight = "aerodatabox.p.rapidapi.com";
    String x_rapidapi_key_flight = "dc45c53089msh5e3bd8147301bdep1be3b9jsnb6a4c8486b54";
    LocalDate departure_date;
    LocalDate return_date;
    String airport_code_from;
    String airport_code_to;
    String currency;

    public Flight(){

    }
    public void setDeparture_date(LocalDate departure_date) {
        this.departure_date = departure_date;
    }

    public void setReturn_date(LocalDate return_date) {
        this.return_date = return_date;
    }

    public void setAirport_code_from(String airport_code_from) {
        this.airport_code_from = airport_code_from;
    }

    public void setAirport_code_to(String airport_code_to) {
        this.airport_code_to = airport_code_to;
    }

    public void setCurrency(String currency){
        this.currency = currency;
    }

    public String getCurrency() {
        return this.currency;
    }


    public void getFlightInfo() throws Exception {

   /*
    HttpResponse<JsonNode> response = Unirest.get("https://skyscanner44.p.rapidapi.com/search?adults=1&origin="+this.airport_code_from+"&destination="+this.airport_code_to+"&departureDate="+departure_date+"&currency=EUR")
            .header("X-RapidAPI-Key", "dc45c53089msh5e3bd8147301bdep1be3b9jsnb6a4c8486b54")
            .header("X-RapidAPI-Host", "skyscanner44.p.rapidapi.com")
            .asJson();
        int iter=0;
        System.out.println(response.getBody());
        System.out.println(response.getBody().getArray().getJSONObject(iter).get("itineraries.buckets").toString());

        while(response.getBody().getArray().iterator().hasNext()){




            iter++;
        }
    JsonArray jarray = jobject.getAsJsonArray("iterinaries.buckets");
    for (int i = 0; i < jarray.size(); i++) {
        jobject = jarray.get(i).getAsJsonObject();
        String price = jobject.get("items[0].price.formatted").toString();
        String departureDate_time = jobject.get("items[0].legs[0].departure").getAsString();
        String arrivalDate_time = jobject.get("items[0].legs[0].arrival").getAsString();
        String duration = jobject.get("items[0].legs[0].durationInMinutes").getAsString();
        String airline_name = jobject.get("items[0].legs[0].carriers.marketing[0].name").getAsString();
        String airline_url_logo = jobject.get("items[0].legs[0].carriers.marketing[0].logoUrl").getAsString();

        System.out.println(price+" "+departureDate_time+""+arrivalDate_time+""+duration+""+airline_name+""+airline_url_logo);
         */

    }


}
