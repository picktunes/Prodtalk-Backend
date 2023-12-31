package prodtalk.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude
public class Pessoa {

    private Long idPessoa;
    private String nomeCompleto;
    private String sexo;
    private Integer idade;
    private String profissao;
    private String biografia;
    private String fotoPerfil;
    
    public Pessoa(Long idPessoa, String nomeCompleto, String sexo, Integer idade, String profissao, String fotoPerfil, String biografia) {
        this.idPessoa = idPessoa;
        this.nomeCompleto = nomeCompleto;
        this.sexo = sexo;
        this.idade = idade;
        this.profissao = profissao;
        this.fotoPerfil = fotoPerfil;
        this.biografia = biografia;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
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

    /*public Blob getImg() {
        return fotoPerfil;
    }

    public void setImg(Blob img) {
        this.fotoPerfil = img;
    }*/

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

}
