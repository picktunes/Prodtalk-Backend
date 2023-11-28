package prodtalk.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

public class Denuncia {

    private Long idDenuncia;
    private Long idPessoa;
    private String dataDenuncia;
    private String descricao;
    private String status;
    private Long tipoDenunciaId;
    private Long idPublicacao;
    private Long idComentario;
    private Long idCategoria;

    public Denuncia() {
    }

    public Denuncia(
        Long idDenuncia,
        Long idPessoa,
        String dataDenuncia,
        String descricao,
        String status,
        Long tipoDenunciaId,
        Long idPublicacao,
        Long idComentario,
        Long idCategoria
    ) {
        this.idDenuncia = idDenuncia;
        this.idPessoa = idPessoa;
        this.dataDenuncia = dataDenuncia;
        this.descricao = descricao;
        this.status = status;
        this.tipoDenunciaId = tipoDenunciaId;
        this.idPublicacao = idPublicacao;
        this.idComentario = idComentario;
        this.idCategoria = idCategoria;
    }

    public Long getIdDenuncia() {
        return idDenuncia;
    }

    public void setIdDenuncia(Long idDenuncia) {
        this.idDenuncia = idDenuncia;
    }

    public Long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getDataDenuncia() {
        return dataDenuncia;
    }

    public void setDataDenuncia(String dataDenuncia) {
        this.dataDenuncia = dataDenuncia;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getTipoDenunciaId() {
        return tipoDenunciaId;
    }

    public void setTipoDenunciaId(Long tipoDenunciaId) {
        this.tipoDenunciaId = tipoDenunciaId;
    }

    public Long getIdPublicacao() {
        return idPublicacao;
    }

    public void setIdPublicacao(Long idPublicacao) {
        this.idPublicacao = idPublicacao;
    }

    public Long getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(Long idComentario) {
        this.idComentario = idComentario;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }
}
