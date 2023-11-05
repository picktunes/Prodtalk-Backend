package prodtalk.entity;

import java.sql.Timestamp;

public class PublicacaoSalva {

    private Long idPublicacaoSalva;
    private Long idPessoa;
    private Long idPublicacao;
    private Timestamp dataSalvamento;

    public PublicacaoSalva() {
    }

    public PublicacaoSalva(Long idPublicacaoSalva, Long idPessoa, Long idPublicacao, Timestamp dataSalvamento) {
        this.idPublicacaoSalva = idPublicacaoSalva;
        this.idPessoa = idPessoa;
        this.idPublicacao = idPublicacao;
        this.dataSalvamento = dataSalvamento;
    }

    public Long getIdPublicacaoSalva() {
        return idPublicacaoSalva;
    }

    public void setIdPublicacaoSalva(Long idPublicacaoSalva) {
        this.idPublicacaoSalva = idPublicacaoSalva;
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

    public Timestamp getDataSalvamento() {
        return dataSalvamento;
    }

    public void setDataSalvamento(Timestamp dataSalvamento) {
        this.dataSalvamento = dataSalvamento;
    }
}
