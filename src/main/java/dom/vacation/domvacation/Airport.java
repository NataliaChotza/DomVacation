package dom.vacation.domvacation;



import java.util.ArrayList;
import java.util.List;

public class Airport {

    //  private List <JsonObject>resultSet_List;
    private String city;
    private List<String> airportList;
    private  String airportName;
    private String country;

    private String airport_code;
    public Airport(String city, String airportName, String country, String airport_code){
        this.city=city;
        this.country=country;
        this.airport_code=airport_code;
        this.airportName=airportName;
        airportList= new ArrayList<>();
        airportList.add(airportName);

    }
    public Airport(){

    }


    public String getAirport_code() {
        return airport_code;
    }

    public String getCountry() {
        return country;
    }

    public synchronized String getAirportName() {

        return airportName;

    }

    public List<String> getAirportList() {
        return airportList;
    }

    public String getCity() {
        return city;
    }


    public void setAirport_code(String airport_code){
        this.airport_code=airport_code;
    }
}
