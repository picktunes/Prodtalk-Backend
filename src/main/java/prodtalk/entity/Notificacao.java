package prodtalk.entity;

public class Notificacao {
    private Long idTipoNotificacao;
    private Publicacao publicacao;
    private int nrNotificacoes;

    public Notificacao( Long idTipoNotificacao, Publicacao publicacao, int nrNotificacoes) {
        this.idTipoNotificacao = idTipoNotificacao;
        this.publicacao = publicacao;
        this.nrNotificacoes = nrNotificacoes;
    }

    public Notificacao() {
        
    }

    public int getNrNotificacoes() {
        return nrNotificacoes;
    }

    public void setNrNotificacoes(int nrNotificacoes) {
        this.nrNotificacoes = nrNotificacoes;
    }

    public Long getIdTipoNotificacao() {
        return idTipoNotificacao;
    }

    public void setIdTipoNotificacao(Long idTipoNotificacao) {
        this.idTipoNotificacao = idTipoNotificacao;
    }

    public Publicacao getPublicacao() {
        return publicacao;
    }

    public void setPublicacao(Publicacao publicacao) {
        this.publicacao = publicacao;
    }
}
