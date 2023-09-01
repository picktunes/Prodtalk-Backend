package prodtalk.repository;

import utils.http.BancoDadosConfig;

public class GenericRepository {
    private static GenericRepository instance;
    private String URL;
    private String USERNAME;
    private String PASSWORD;
    
    public GenericRepository() {
        BancoDadosConfig bdc = new BancoDadosConfig();
        this.setURL(bdc.getUrl());
        this.setUSERNAME(bdc.getUsername());
        this.setPASSWORD(bdc.getPassword());
    }
    
    public static GenericRepository getInstance() {
        if (instance == null) {
            instance = new GenericRepository();
        }
        return instance;
    }
    
    public String getURL() {
        return URL;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }
    
    public String setURL(String url) {
        return this.URL = url;
    }

    public String setUSERNAME(String username) {
        return this.USERNAME = username;
    }

    public String setPASSWORD(String password) {
        return this.PASSWORD = password;
    }
}