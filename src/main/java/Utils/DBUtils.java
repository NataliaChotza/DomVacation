package Utils;

import java.sql.*;

public class DBUtils {
    private static Connection con;
    private static ResultSet rs;
    private static String DB= "jdbc:mysql://localhost/dbairlines";
    private static String DB_PASSWORD = "Natalia1999";
    private static String DB_USER = "root";
    private static boolean matches =false;
    public static String clientTableSQL= "SELECT * FROM client";

    public static void connectToDB(String sql) throws SQLException {
        DBUtils.setCon(DriverManager.getConnection(DB,DB_USER,DB_PASSWORD));
        try {
            Statement st = getCon().createStatement();
            rs= st.executeQuery(sql);
        }catch (SQLException exception){
            System.err.print(exception.getErrorCode());
        }
    }
    public static boolean correctLogInData(String email,String password) throws SQLException {
        while(getRs().next()){
                String cEmail = getRs().getString("email");
                String cPassword = getRs().getString("password");
                matches = cEmail.matches(email) && cPassword.matches(password);
            if(matches)
                break;
        }

        return matches;

    }

    public static String getUserPassword(String email) throws SQLException {
        while (getRs().next()) {
            String cEmail = getRs().getString("email");
            matches = email.matches(cEmail);
            if (matches)
                return getRs().getString("password");
        }

        return null;
    }

    public static boolean userExist(String email) throws SQLException {
        while(getRs().next()){
                String cEmail = getRs().getString("email");
                matches = email.matches(cEmail);
            if(matches)
                break;
        }

            return matches;

    }
    public static void addUser(String email,String password,String name) throws SQLException {
        executeUpdate("INSERT into client(email,password,name) VALUES('"+ email+"','"+password+"','"+name+"')");
        closeConnection();
    }
    public static void executeUpdate(String sql) throws SQLException {
            try {
                getCon().createStatement().executeUpdate(sql);
            }catch (SQLException ex){
                ex.printStackTrace();
            }
    }
    public static Connection getCon(){
        return con;
    }
    public static void setCon(Connection con){
        DBUtils.con =con;
    }
    public static ResultSet getRs(){
        return rs;
    }
    public static void closeConnection() throws SQLException {
        getCon().close();
    }
}
