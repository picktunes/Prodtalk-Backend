package prodtalk.entity;


public class Pessoa {

    private Long idPessoa;
    private String nomeCompleto;
    private String sexo;
    private Integer idade;
    private String profissao;
    //private Blob fotoPerfil;
    private String biografia;
    private String interesses;
    
    public Pessoa(Long idPessoa, String nomeCompleto, String sexo, Integer idade, String profissao, /*Blob fotoPerfil,*/ String biografia, String interesses) {
        this.idPessoa = idPessoa;
        this.nomeCompleto = nomeCompleto;
        this.sexo = sexo;
        this.idade = idade;
        this.profissao = profissao;
        //this.fotoPerfil = fotoPerfil;
        this.biografia = biografia;
        this.interesses = interesses;
    }
    
    public Long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    /*public Blob getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(Blob fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }*/

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String getInteresses() {
        return interesses;
    }

    public void setInteresses(String interesses) {
        this.interesses = interesses;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

}
