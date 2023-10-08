package prodtalk.entity;

import java.sql.Timestamp;

public class Comentario {

    private Long idComentario;
    private Pessoa pessoa;
    private Long idPublicacao;
    private Long idComentarioResposta;
    private String conteudo;
    private Integer nrDenuncias = 0;
    private Timestamp dtCriacaoComent;
    private Integer ieAtivo = 1;
    private Timestamp dtInativo;
    
    public Comentario(Long idComentario, Pessoa pessoa, Long idPublicacao, Long idComentarioResposta, String conteudo, Timestamp dtCriacaoComent, Timestamp dtInativo) {
        this.idComentario = idComentario;
        this.pessoa = pessoa;
        this.idPublicacao = idPublicacao;
        this.idComentarioResposta = idComentarioResposta;
        this.conteudo = conteudo;
        this.dtCriacaoComent = dtCriacaoComent;
        this.dtInativo = dtInativo;
    }

    public Long getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(Long idComentario) {
        this.idComentario = idComentario;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
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
