package prodtalk.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import prodtalk.entity.Pessoa;
import prodtalk.entity.PublicacaoCurtida;

@Repository
public class PublicacaoCurtidaRepository extends GenericRepository {

    public List<PublicacaoCurtida> buscarPublicacaoCurtidaPorPublicacao(long idPublicacao) throws Exception {
        List<PublicacaoCurtida> publicacoesCurtidas = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(getURL(), getUSERNAME(), getPASSWORD());

            String query = "SELECT * FROM publicacao_curtida WHERE ID_PUBLICACAO = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, idPublicacao);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Pessoa pessoa = instanciarPessoa(resultSet);
                PublicacaoCurtida publicacaoCurtida = new PublicacaoCurtida(
                        resultSet.getInt("ID_CURTIDA"),
                        resultSet.getInt("ID_PUBLICACAO"),
                        pessoa,
                        resultSet.getDate("DT_CURTIDA")
                );

                publicacoesCurtidas.add(publicacaoCurtida);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return publicacoesCurtidas;
    }

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

}
