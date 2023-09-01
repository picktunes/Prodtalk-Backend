package prodtalk.entity;

import java.util.Date;

public class Interesse {

    private long idInteresse;
    private String dsInteresse;
    private Date dtCriacao;
    private Date dtAtualizacao;
    private String ieAtivo;

    public Interesse(long idInteresse, String dsInteresse, Date dtCriacao, Date dtAtualizacao, String ieAtivo) {
        this.idInteresse = idInteresse;
        this.dsInteresse = dsInteresse;
        this.dtCriacao = dtCriacao;
        this.dtAtualizacao = dtAtualizacao;
        this.ieAtivo = ieAtivo;
    }

    public long getIdInteresse() {
        return idInteresse;
    }

    public void setIdInteresse(long idInteresse) {
        this.idInteresse = idInteresse;
    }

    public String getDsInteresse() {
        return dsInteresse;
    }

    public void setDsInteresse(String dsInteresse) {
        this.dsInteresse = dsInteresse;
    }

    public Date getDtCriacao() {
        return dtCriacao;
    }

    public void setDtCriacao(Date dtCriacao) {
        this.dtCriacao = dtCriacao;
    }

    public Date getDtAtualizacao() {
        return dtAtualizacao;
    }

    public void setDtAtualizacao(Date dtAtualizacao) {
        this.dtAtualizacao = dtAtualizacao;
    }

    public String getIeAtivo() {
        return ieAtivo;
    }

    public void setIeAtivo(String ieAtivo) {
        this.ieAtivo = ieAtivo;
    }

}