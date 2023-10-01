package prodtalk.entity;

import java.util.Date;

public class PublicacaoCurtida {

    private long idCurtida;
    private long publicacao;
    private Pessoa pessoa;
    private Date dataCurtida;

    public PublicacaoCurtida(int idCurtida, long publicacao, Pessoa pessoa, Date dataCurtida) {
        this.idCurtida = idCurtida;
        this.publicacao = publicacao;
        this.pessoa = pessoa;
        this.dataCurtida = dataCurtida;
    }

    public long getIdCurtida() {
        return idCurtida;
    }

    public void setIdCurtida(long idCurtida) {
        this.idCurtida = idCurtida;
    }

    public long getPublicacao() {
        return publicacao;
    }

    public void setPublicacao(long publicacao) {
        this.publicacao = publicacao;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Date getDataCurtida() {
        return dataCurtida;
    }

    public void setDataCurtida(Date dataCurtida) {
        this.dataCurtida = dataCurtida;
    }
}
