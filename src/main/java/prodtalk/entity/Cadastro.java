package entity;

public class Cadastro {

    private long idCadastro;
    private String email;
    private String login;
    private String senha;
    private int statusCadastro;

    public Cadastro(long idCadastro, String email, String login, String senha, int statusCadastro) {
        this.idCadastro = idCadastro;
        this.email = email;
        this.login = login;
        this.senha = senha;
        this.statusCadastro = statusCadastro;
    }
    
    public long getIdCadastro() {
        return idCadastro;
    }

    public void setIdCadastro(long idCadastro) {
        this.idCadastro = idCadastro;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public int getStatusCadastro() {
        return statusCadastro;
    }

    public void setStatusCadastro(int statusCadastro) {
        this.statusCadastro = statusCadastro;
    }

}