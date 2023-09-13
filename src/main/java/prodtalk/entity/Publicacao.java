package prodtalk.entity;

import java.sql.Blob;
import java.util.Date;

public class Publicacao {

    private int idPublicacao;
    private int idPessoa;
    private Date dataCriacao;
    private Date dataAtualizacao;
    private String conteudo;
    private String titulo;
    private int quantidadeLikes;
    private String img;

    public Publicacao() {
    }

    public Publicacao(int idPublicacao, int idPessoa, Date dataCriacao, Date dataAtualizacao, String conteudo, String titulo, int quantidadeLikes, String img) {
        this.idPublicacao = idPublicacao;
        this.idPessoa = idPessoa;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
        this.conteudo = conteudo;
        this.titulo = titulo;
        this.quantidadeLikes = quantidadeLikes;
        this.img = img;
    }

    public int getIdPublicacao() {
        return idPublicacao;
    }

    public void setIdPublicacao(int idPublicacao) {
        this.idPublicacao = idPublicacao;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public int getQuantidadeLikes() {
        return quantidadeLikes;
    }

    public void setQuantidadeLikes(int quantidadeLikes) {
        this.quantidadeLikes = quantidadeLikes;
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
