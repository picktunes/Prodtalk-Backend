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
                    + "    id_publicacao = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, idPublicacao);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Map<String, Object> comentario = new HashMap<>();
                comentario.put("idComentario", resultSet.getLong("id_comentario"));
                comentario.put("idPessoa", resultSet.getLong("id_pessoa"));
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

        return comentariosHierarquicos;
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


    /*
    public Optional<PublicacaoCurtida> findByPublicacaoAndPessoa(long idPublicacao, long idPessoa) throws Exception {
        Optional<PublicacaoCurtida> publicacaoCurtidaOptional = Optional.empty();

        try {
            Connection connection = DriverManager.getConnection(getURL(), getUSERNAME(), getPASSWORD());

            String query = "SELECT * FROM publicacao_curtida WHERE ID_PUBLICACAO = ? AND ID_PESSOA = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, idPublicacao);
            statement.setLong(2, idPessoa);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Pessoa pessoa = instanciarPessoa(resultSet);
                PublicacaoCurtida publicacaoCurtida = new PublicacaoCurtida(
                        resultSet.getInt("ID_CURTIDA"),
                        resultSet.getInt("ID_PUBLICACAO"),
                        pessoa,
                        resultSet.getDate("DT_CURTIDA")
                );

                publicacaoCurtidaOptional = Optional.of(publicacaoCurtida);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return publicacaoCurtidaOptional;
    }

    public List<PublicacaoCurtida> save(long idPublicacao, long idPessoa) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(getURL(), getUSERNAME(), getPASSWORD());

            String sql = "INSERT INTO publicacao_curtida (ID_CURTIDA, ID_PUBLICACAO, ID_PESSOA, DT_CURTIDA) VALUES (SEQ_PUBLICACAO_CURTIDA.nextval, ?, ?, sysdate)";
            statement = connection.prepareStatement(sql);
            statement.setLong(1, idPublicacao);
            statement.setLong(2, idPessoa);

            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return buscarPublicacaoCurtidaPorPublicacao(idPublicacao);
    }

    public List<PublicacaoCurtida> delete(long idPublicacao, long idPessoa) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(getURL(), getUSERNAME(), getPASSWORD());

            String sql = "DELETE FROM publicacao_curtida WHERE ID_PUBLICACAO = ? and ID_PESSOA = ?";
            statement = connection.prepareStatement(sql);
            statement.setLong(1, idPublicacao);
            statement.setLong(2, idPessoa);

            statement.execute();

        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return buscarPublicacaoCurtidaPorPublicacao(idPublicacao);
    }
     */
}
