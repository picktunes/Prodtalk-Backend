package prodtalk.entity;

import java.sql.Timestamp;
public class Comentario {

    private Long idComentario;
    private Long idPessoa;
    private Long idPublicacao;
    private Long idComentarioResposta;
    private String conteudo;
    private Integer nrDenuncias = 0;
    private Timestamp dtCriacaoComent;
    private Integer ieAtivo = 1;
    private Timestamp dtInativo;

    public Long getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(Long idComentario) {
        this.idComentario = idComentario;
    }

    public Long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }

    public Long getIdPublicacao() {
        return idPublicacao;
    }

    public void setIdPublicacao(Long idPublicacao) {
        this.idPublicacao = idPublicacao;
    }

    public Long getIdComentarioResposta() {
        return idComentarioResposta;
    }

    public void setIdComentarioResposta(Long idComentarioResposta) {
        this.idComentarioResposta = idComentarioResposta;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Integer getNrDenuncias() {
        return nrDenuncias;
    }

    public void setNrDenuncias(Integer nrDenuncias) {
        this.nrDenuncias = nrDenuncias;
    }

    public Timestamp getDtCriacaoComent() {
        return dtCriacaoComent;
    }

    public void setDtCriacaoComent(Timestamp dtCriacaoComent) {
        this.dtCriacaoComent = dtCriacaoComent;
    }

    public Integer getIeAtivo() {
        return ieAtivo;
    }

    public void setIeAtivo(Integer ieAtivo) {
        this.ieAtivo = ieAtivo;
    }

    public Timestamp getDtInativo() {
        return dtInativo;
    }

    public void setDtInativo(Timestamp dtInativo) {
        this.dtInativo = dtInativo;
    }

}
