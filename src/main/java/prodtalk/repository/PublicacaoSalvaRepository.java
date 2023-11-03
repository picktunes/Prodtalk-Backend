package prodtalk.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import prodtalk.entity.PublicacaoSalva;

@Repository
public class PublicacaoSalvaRepository extends GenericRepository {

    public void inserirPublicacaoSalva(long idPessoa, long idPublicacao) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(getURL(), getUSERNAME(), getPASSWORD());

            String sql = "INSERT INTO publicacao_salva (ID_PUBLICACAO_SALVA, ID_PESSOA, ID_PUBLICACAO, DATA_SALVAMENTO) "
                    + "VALUES (PUBLICACAO_SALVA_SEQ.NEXTVAL, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setLong(1, idPessoa);
            statement.setLong(2, idPublicacao);
            statement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));

            statement.executeUpdate();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void excluirPublicacaoSalva(long idPessoa, long idPublicacao) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(getURL(), getUSERNAME(), getPASSWORD());

            String sql = "DELETE FROM publicacao_salva WHERE ID_PESSOA = ? and ID_PUBLICACAO = ?";
            statement = connection.prepareStatement(sql);
            statement.setLong(1, idPessoa);
            statement.setLong(2, idPublicacao);

            statement.executeUpdate();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public PublicacaoSalva buscarPublicacaoSalvaPorID(long idPublicacaoSalva) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(getURL(), getUSERNAME(), getPASSWORD());

            String query = "SELECT * FROM publicacao_salva WHERE ID_PUBLICACAO_SALVA = ?";
            statement = connection.prepareStatement(query);
            statement.setLong(1, idPublicacaoSalva);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                PublicacaoSalva publicacaoSalva = new PublicacaoSalva();
                publicacaoSalva.setIdPublicacaoSalva(resultSet.getLong("ID_PUBLICACAO_SALVA"));
                publicacaoSalva.setIdPessoa(resultSet.getLong("ID_PESSOA"));
                publicacaoSalva.setIdPublicacao(resultSet.getLong("ID_PUBLICACAO"));
                publicacaoSalva.setDataSalvamento(resultSet.getTimestamp("DATA_SALVAMENTO"));

                return publicacaoSalva;
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return null;
    }

    public void atualizarPublicacaoSalva(PublicacaoSalva publicacaoSalva) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(getURL(), getUSERNAME(), getPASSWORD());

            String sql = "UPDATE publicacao_salva SET ID_PESSOA = ?, ID_PUBLICACAO = ?, DATA_SALVAMENTO = ? WHERE ID_PUBLICACAO_SALVA = ?";
            statement = connection.prepareStatement(sql);
            statement.setLong(1, publicacaoSalva.getIdPessoa());
            statement.setLong(2, publicacaoSalva.getIdPublicacao());
            statement.setTimestamp(3, publicacaoSalva.getDataSalvamento());
            statement.setLong(4, publicacaoSalva.getIdPublicacaoSalva());

            statement.executeUpdate();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public List<PublicacaoSalva> buscarPublicacoesSalvasPorPessoa(long idPessoa) throws SQLException {
        List<PublicacaoSalva> publicacoesSalvas = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(getURL(), getUSERNAME(), getPASSWORD());

            String query = "SELECT * FROM publicacao_salva WHERE ID_PESSOA = ?";
            statement = connection.prepareStatement(query);
            statement.setLong(1, idPessoa);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                PublicacaoSalva publicacaoSalva = new PublicacaoSalva();
                publicacaoSalva.setIdPublicacaoSalva(resultSet.getLong("ID_PUBLICACAO_SALVA"));
                publicacaoSalva.setIdPessoa(resultSet.getLong("ID_PESSOA"));
                publicacaoSalva.setIdPublicacao(resultSet.getLong("ID_PUBLICACAO"));
                publicacaoSalva.setDataSalvamento(resultSet.getTimestamp("DATA_SALVAMENTO"));

                publicacoesSalvas.add(publicacaoSalva);
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return publicacoesSalvas;
    }

    public boolean verificarPublicacaoSalva(long idPessoa, long idPublicacao) throws SQLException {
        boolean publicacaoSalva = false;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(getURL(), getUSERNAME(), getPASSWORD());

            String query = "SELECT 1 FROM publicacao_salva WHERE ID_PESSOA = ? AND ID_PUBLICACAO = ? and rownum = 1";
            statement = connection.prepareStatement(query);
            statement.setLong(1, idPessoa);
            statement.setLong(2, idPublicacao);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                publicacaoSalva = true;
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return publicacaoSalva;
    }
}
