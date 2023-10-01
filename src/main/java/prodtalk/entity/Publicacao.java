package prodtalk.entity;

import java.util.Date;
import java.util.List;

public class Publicacao {

    private long idPublicacao;
    private Pessoa pessoa;
    private List<PublicacaoCurtida> publicacaoCurtida;
    private int idPessoa;
    private Date dataCriacao;
    private Date dataAtualizacao;
    private String conteudo;
    private String titulo;
    private String img;

    public Publicacao(Pessoa pessoa, long idPublicacao, int idPessoa, Date dataCriacao, Date dataAtualizacao, String conteudo, String titulo, List<PublicacaoCurtida>  publicacaoCurtida, String img) {
        this.pessoa = pessoa;
        this.idPublicacao = idPublicacao;
        this.idPessoa = idPessoa;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
        this.conteudo = conteudo;
        this.titulo = titulo;
        this.publicacaoCurtida = publicacaoCurtida;
        this.img = img;
    }

    public long getIdPublicacao() {
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

    public List<PublicacaoCurtida>  getPublicacaoCurtidas() {
        return publicacaoCurtida;
    }

    public void setPublicacaoCurtidas(List<PublicacaoCurtida>  publicacaoCurtidas) {
        this.publicacaoCurtida = publicacaoCurtidas;
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
    
    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
