package prodtalk.repository;

import java.io.IOException;
import prodtalk.entity.Categoria;
import utils.http.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import prodtalk.entity.Notificacao;
import prodtalk.entity.Publicacao;

@Repository
public class NotificacaoRepository extends GenericRepository {

    public List<Notificacao> buscarNotificacoesPorIdPessoa(Long idPessoa) throws SQLException, Exception {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(getURL(), getUSERNAME(), getPASSWORD());

            String sql = "SELECT p.ID_PUBLICACAO, n.ID_TIPO_NOTIFICACAO, COUNT(n.ID_TIPO_NOTIFICACAO) as NUMERO_NOTIFICACOES "
                    + "FROM notificacao n "
                    + "INNER JOIN publicacao p ON n.ID_PUBLICACAO = p.ID_PUBLICACAO "
                    + "WHERE p.ID_PESSOA = ? AND n.ID_PESSOA <> ? "
                    + "GROUP BY p.ID_PUBLICACAO, n.ID_TIPO_NOTIFICACAO "
                    + "ORDER BY p.ID_PUBLICACAO desc, n.ID_TIPO_NOTIFICACAO";

            statement = connection.prepareStatement(sql);
            statement.setLong(1, idPessoa);
            statement.setLong(2, idPessoa);

            ResultSet resultSet = statement.executeQuery();
            List<Notificacao> notificacoes = new ArrayList<>();

            while (resultSet.next()) {

                Publicacao publicacao = instanciarPublicacao(resultSet);

                Notificacao notificacao = new Notificacao(
                        resultSet.getLong("ID_TIPO_NOTIFICACAO"),
                        publicacao,
                        resultSet.getInt("NUMERO_NOTIFICACOES")
                );

                notificacoes.add(notificacao);
            }

            return notificacoes;
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

}
