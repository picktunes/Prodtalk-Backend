package prodtalk.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import prodtalk.entity.Comentario;
import prodtalk.entity.ComentarioHierarquico;
import prodtalk.entity.Pessoa;

@Repository
public class ComentarioRepository extends GenericRepository {

    public List<Map<String, Object>> buscarComentariosPorPublicacao(long idPublicacao) throws SQLException {
        List<Map<String, Object>> comentariosHierarquicos = new ArrayList<>();
        Map<Long, Map<String, Object>> comentarioMap = new HashMap<>();

        try {
            Connection connection = DriverManager.getConnection(getURL(), getUSERNAME(), getPASSWORD());

            String query = "SELECT \n"
                    + "    ID_COMENTARIO,\n"
                    + "    ID_PESSOA,\n"
                    + "    ID_PUBLICACAO,\n"
                    + "    ID_COMENTARIO_RESPOSTA,\n"
                    + "    CONTEUDO,\n"
                    + "    NR_DENUNCIAS,\n"
                    + "    DT_CRIACAO_COMENT,\n"
                    + "    IE_ATIVO,\n"
                    + "    DT_INATIVO\n"
                    + "FROM \n"
                    + "    comentario \n"
                    + "WHERE \n"
                    + "    id_publicacao = ?"
                    + "    order by id_comentario desc";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, idPublicacao);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Map<String, Object> comentario = new HashMap<>();
                Pessoa pessoa = instanciarPessoa(resultSet);
                comentario.put("idComentario", resultSet.getLong("id_comentario"));
                comentario.put("pessoa", pessoa);
                comentario.put("idPublicacao", resultSet.getLong("id_publicacao"));
                comentario.put("idComentarioResposta", resultSet.getLong("ID_COMENTARIO_RESPOSTA"));
                comentario.put("conteudo", resultSet.getString("conteudo"));
                comentario.put("nrDenuncias", resultSet.getInt("nr_denuncias"));
                comentario.put("dtCriacaoComent", resultSet.getTimestamp("dt_criacao_coment"));
                comentario.put("ieAtivo", resultSet.getInt("ie_ativo"));
                comentario.put("dtInativo", resultSet.getTimestamp("dt_inativo"));
                comentario.put("respostas", new ArrayList<Map<String, Object>>());

                Long comentarioId = resultSet.getLong("id_comentario");
                comentarioMap.put(comentarioId, comentario);
            }

            // Construir a estrutura hierárquica
            for (Map<String, Object> comentario : comentarioMap.values()) {
                Long respostaId = (Long) comentario.get("idComentarioResposta");
                if (respostaId != null && respostaId != 0) {
                    Map<String, Object> resposta = comentarioMap.get(respostaId);
                    if (resposta != null) {
                        List<Map<String, Object>> respostas = (List<Map<String, Object>>) resposta.get("respostas");
                        respostas.add(comentario);
                    }
                } else {
                    // É um comentário principal, adicione-o à lista de comentários hierárquicos
                    comentariosHierarquicos.add(comentario);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        Collections.sort(comentariosHierarquicos, (comentario1, comentario2) -> {
    Long id1 = (Long) comentario1.get("idComentario");
    Long id2 = (Long) comentario2.get("idComentario");
    return id2.compareTo(id1); // Ordernar em ordem decrescente
});

        return comentariosHierarquicos;
    }

    public List<Map<String, Object>> salvarComentario(Comentario comentario) throws SQLException, Exception {
        Connection connection = null;
        PreparedStatement statement = null;

        Long comentarioIdResposta = comentario.getIdComentarioResposta();

        try {
            connection = DriverManager.getConnection(getURL(), getUSERNAME(), getPASSWORD());

            String sql = "INSERT INTO COMENTARIO (ID_COMENTARIO, ID_PESSOA, ID_PUBLICACAO, ID_COMENTARIO_RESPOSTA, CONTEUDO, NR_DENUNCIAS, DT_CRIACAO_COMENT, IE_ATIVO, DT_INATIVO)\n"
                    + "VALUES (SEQ_COMENTARIO.NEXTVAL, ?, ?, ?, ?, ?,  SYSDATE, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setLong(1, comentario.getPessoa().getIdPessoa());
            statement.setLong(2, comentario.getIdPublicacao());
            if (comentarioIdResposta == null) {
                statement.setNull(3, java.sql.Types.BIGINT); // Define o campo como nulo
            } else {
                statement.setLong(3, comentarioIdResposta);
            }
            statement.setString(4, comentario.getConteudo());
            statement.setInt(5, 0);
            statement.setInt(6, 1);
            statement.setDate(7, null);

            statement.execute();

            return buscarComentariosPorPublicacao(comentario.getIdPublicacao());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    private ComentarioHierarquico encontrarComentarioHierarquico(List<ComentarioHierarquico> comentariosHierarquicos, Long comentarioId) {
        for (ComentarioHierarquico comentarioHierarquico : comentariosHierarquicos) {
            if (comentarioHierarquico.getComentario().getIdComentario().equals(comentarioId)) {
                return comentarioHierarquico;
            }
            // Verificar as respostas recursivamente
            ComentarioHierarquico resposta = encontrarComentarioHierarquico(comentarioHierarquico.getRespostas(), comentarioId);
            if (resposta != null) {
                return resposta;
            }
        }
        return null;
    }

}
