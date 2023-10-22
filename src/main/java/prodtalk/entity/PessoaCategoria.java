package prodtalk.entity;

import java.sql.Date;

public class PessoaCategoria {

    private Long idPessoaCategoria;
    private Long idPessoa;
    private Long idCategoria;
    private Date dataCriacao;

    public PessoaCategoria(Long idPessoaCategoria, Long idPessoa, Long idCategoria, Date dataCriacao) {
        this.idPessoaCategoria = idPessoaCategoria;
        this.idPessoa = idPessoa;
        this.idCategoria = idCategoria;
        this.dataCriacao = dataCriacao;
    }

    public Long getIdPessoaCategoria() {
        return idPessoaCategoria;
    }

    public void setIdPessoaCategoria(Long idPessoaCategoria) {
        this.idPessoaCategoria = idPessoaCategoria;
    }

    public Long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    // Outros campos, getters e setters
}
