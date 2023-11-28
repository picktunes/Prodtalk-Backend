package prodtalk.entity;

import java.util.ArrayList;
import java.util.List;

public class ComentarioHierarquico {

    private Comentario comentario;
    private List<ComentarioHierarquico> respostas;
    private int qtdComentarios;

    public ComentarioHierarquico(Comentario comentario) {
        this.comentario = comentario;
        this.respostas = new ArrayList<>();
    }

    public Comentario getComentario() {
        return comentario;
    }

    public void setComentario(Comentario comentario) {
        this.comentario = comentario;
    }

    public List<ComentarioHierarquico> getRespostas() {
        return respostas;
    }

    public void setRespostas(List<ComentarioHierarquico> respostas) {
        this.respostas = respostas;
    }

    public int getQtdComentarios() {
        return qtdComentarios;
    }

    public void setQtdComentarios(int qtdComentarios) {
        this.qtdComentarios = qtdComentarios;
    }

    public void adicionarResposta(ComentarioHierarquico resposta) {
        respostas.add(resposta);
    }

    public void calcularContagem() {
        qtdComentarios = respostas.size();
        for (ComentarioHierarquico resposta : respostas) {
            resposta.calcularContagem(); 
            qtdComentarios += resposta.getQtdComentarios();
        }
    }

}
