package utils.http;

public class BancoDadosConfig {
    public static final String URL = "jdbc:oracle:thin:@//localhost:1521/xe";
    public static final String USERNAME = "C##pick";
    public static final String PASSWORD = "pick";

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