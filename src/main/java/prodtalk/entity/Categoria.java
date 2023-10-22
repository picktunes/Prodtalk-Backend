package prodtalk.entity;

import java.sql.Timestamp;

public class Categoria {
    private Long idCategoria;
    private String dsNome;
    private String dsDescricao;
    private String img;
    private String imgCapa;
    private Timestamp dtCriacao;
    private Timestamp dtAtualizacao;
    private String ieStatus;
    private Long idCategoriaPai;
    private Long idPessoaAutor;
    private Long contadorVisualizacoes;
    private long countSeguidores;
    private long countPublicacoes
;
    public Categoria(Long idCategoria, String dsNome, String dsDescricao, String img, String imgCapa, Timestamp dtCriacao,
            Timestamp dtAtualizacao, String ieStatus, Long idCategoriaPai, Long idPessoaAutor, Long contadorVisualizacoes,
            Long countSeguidores, Long countPublicacoes) {
        this.idCategoria = idCategoria;
        this.dsNome = dsNome;
        this.dsDescricao = dsDescricao;
        this.img = img;
        this.imgCapa = imgCapa;
        this.dtCriacao = dtCriacao;
        this.dtAtualizacao = dtAtualizacao;
        this.ieStatus = ieStatus;
        this.idCategoriaPai = idCategoriaPai;
        this.idPessoaAutor = idPessoaAutor;
        this.contadorVisualizacoes = contadorVisualizacoes;
        this.countSeguidores = countSeguidores;
        this.countPublicacoes = countPublicacoes;
    }

    public Categoria() {
        
    }
   
    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getDsNome() {
        return dsNome;
    }

    public void setDsNome(String dsNome) {
        this.dsNome = dsNome;
    }

    public String getDsDescricao() {
        return dsDescricao;
    }

    public void setDsDescricao(String dsDescricao) {
        this.dsDescricao = dsDescricao;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImgCapa() {
        return imgCapa;
    }

    public void setImgCapa(String imgCapa) {
        this.imgCapa = imgCapa;
    }

    public Timestamp getDtCriacao() {
        return dtCriacao;
    }

    public void setDtCriacao(Timestamp dtCriacao) {
        this.dtCriacao = dtCriacao;
    }

    public Timestamp getDtAtualizacao() {
        return dtAtualizacao;
    }

    public void setDtAtualizacao(Timestamp dtAtualizacao) {
        this.dtAtualizacao = dtAtualizacao;
    }

    public String getIeStatus() {
        return ieStatus;
    }

    public void setIeStatus(String ieStatus) {
        this.ieStatus = ieStatus;
    }

    public Long getIdCategoriaPai() {
        return idCategoriaPai;
    }

    public void setIdCategoriaPai(Long idCategoriaPai) {
        this.idCategoriaPai = idCategoriaPai;
    }

    public Long getIdPessoaAutor() {
        return idPessoaAutor;
    }

    public void setIdPessoaAutor(Long idPessoaAutor) {
        this.idPessoaAutor = idPessoaAutor;
    }

    public Long getContadorVisualizacoes() {
        return contadorVisualizacoes;
    }

    public void setContadorVisualizacoes(Long contadorVisualizacoes) {
        this.contadorVisualizacoes = contadorVisualizacoes;
    }
    
    public long getCountSeguidores() {
        return countSeguidores;
    }

    public void setCountSeguidores(long countSeguidores) {
        this.countSeguidores = countSeguidores;
    }

    public long getCountPublicacoes() {
        return countPublicacoes;
    }

    public void setCountPublicacoes(long countPublicacoes) {
        this.countPublicacoes = countPublicacoes;
    }
}
