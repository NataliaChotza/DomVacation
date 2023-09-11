package Utils;

import dom.vacation.domvacation.Airport;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class DBUtils {
    private static Connection con;
    private static ResultSet rs;
    private static String DB= "jdbc:mysql://localhost/dbairlines";
    private static String DB_PASSWORD = "Natalia1999";
    private static String DB_USER = "root";
    static boolean matches = false;
    public static String clientTableSQL= "SELECT * FROM client";
    public static String airportDataSQL = "SELECT city.name as 'city', airport.name as 'airport' FROM airport INNER JOIN city ON airport.id_airport=city.id_airport";

    static Logger logger= Logger.getLogger("DBUtilsLogger");
    public static void connectToDB(String sql) throws SQLException {
        logger.info("Connected to database");
        DBUtils.setCon(DriverManager.getConnection(DB,DB_USER,DB_PASSWORD));
        try {
            Statement st = getCon().createStatement();
            setRs(st.executeQuery(sql));

        }catch (SQLException exception){
            System.err.print(exception.getErrorCode());
        }
    }
    public static boolean correctLogInData(String email,String password) throws SQLException {
        logger.info("Checked if login data are correct");
        while(getRs().next()){
                String cEmail = getRs().getString("email");
                String cPassword = getRs().getString("password");
                matches=cEmail.matches(email) && cPassword.matches(password);
                if(matches)
                    break;
        }

        return matches;

    }

    public static String getUserPassword(String email) throws SQLException {
        logger.info("Retrieved user password");
        while (getRs().next()) {
            String cEmail = getRs().getString("email");
            matches=email.matches(cEmail);
            if (matches)
                return getRs().getString("password");
        }

        return null;
    }

    public static boolean userExistByEmail(String email) throws SQLException {
       logger.info("Checked if user exist by given email");
        while(getRs().next()){
                String cEmail = getRs().getString("email");
                matches=email.matches(cEmail);
                if(matches)
                break;
        }
            return matches;

    }
    public static void addUser(String email,String password,String name) throws SQLException {
        logger.info("Added new user");
        executeUpdate("INSERT into client(email,password,name) VALUES('"+ email+"','"+password+"','"+name+"')");
        closeConnection();
    }
    public static void executeUpdate(String sql) throws SQLException {
        logger.info("Updated database with new data");
            try {
                getCon().createStatement().executeUpdate(sql);
            }catch (SQLException ex){
                ex.printStackTrace();
            }
    }
    public static Map<String,String> getAirportData(){
        logger.info("Retrieved data from airport table");
        Map<String,String> airportMap = new HashMap<>();
        try{
            while(getRs().next()){
                airportMap.put(getRs().getString("airport"),getRs().getString("city"));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }

        return airportMap;
    }
    public static Connection getCon(){
        return con;
    }
    public static void setCon(Connection con){
        DBUtils.con =con;
    }
    public static void setRs(ResultSet resultSet){
        rs=resultSet;
    }
    public static ResultSet getRs(){
        return rs;
    }
    public static void closeConnection() throws SQLException {
        getCon().close();
    }
}
