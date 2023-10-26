package prodtalk.entity;
import java.util.Date;

public class Notificacao {
    private Long idNotificacao;
    private Long idTipoNotificacao;
    private Long idPublicacao;
    private Long idPessoa;
    private Date dataNotificacao;
    private String status;

    public Notificacao(Long idNotificacao, Long idTipoNotificacao, Long idPublicacao, Long idPessoa, Date dataNotificacao, String status) {
        this.idNotificacao = idNotificacao;
        this.idTipoNotificacao = idTipoNotificacao;
        this.idPublicacao = idPublicacao;
        this.idPessoa = idPessoa;
        this.dataNotificacao = dataNotificacao;
        this.status = status;
    }

    public Notificacao() {
        
    }

    public Long getIdNotificacao() {
        return idNotificacao;
    }

    public void setIdNotificacao(Long idNotificacao) {
        this.idNotificacao = idNotificacao;
    }

    public Long getIdTipoNotificacao() {
        return idTipoNotificacao;
    }

    public void setIdTipoNotificacao(Long idTipoNotificacao) {
        this.idTipoNotificacao = idTipoNotificacao;
    }

    public Long getIdPublicacao() {
        return idPublicacao;
    }

    public void setIdPublicacao(Long idPublicacao) {
        this.idPublicacao = idPublicacao;
    }

    public Long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }

    public Date getDataNotificacao() {
        return dataNotificacao;
    }

    public void setDataNotificacao(Date dataNotificacao) {
        this.dataNotificacao = dataNotificacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
