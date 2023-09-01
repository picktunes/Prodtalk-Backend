package utils.http;

public class BancoDadosConfig {
    public static final String URL = "jdbc:oracle:thin:@//localhost:1521/xe";
    public static final String USERNAME = "sys as sysdba";
    public static final String PASSWORD = "123";

    public String getUrl() {
        return URL;
    }

    public String getUsername() {
        return USERNAME;
    }

    public String getPassword() {
        return PASSWORD;
    }
}