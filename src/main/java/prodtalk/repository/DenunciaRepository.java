package prodtalk.repository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import org.springframework.http.ResponseEntity;
import prodtalk.entity.Denuncia;
import utils.http.Response;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class DenunciaRepository extends GenericRepository {

    public Denuncia getDenuncia(long idDenuncia) throws SQLException, IOException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(getURL(), getUSERNAME(), getPASSWORD());

            String sql = "SELECT ID_DENUNCIA, ID_PESSOA, DATA_DENUNCIA, DESCRICAO, STATUS, TIPO_DENUNCIA_ID, ID_PUBLICACAO, ID_COMENTARIO, ID_CATEGORIA "
                    + "FROM denuncia "
                    + "WHERE ID_DENUNCIA = ?";

            statement = connection.prepareStatement(sql);
            statement.setLong(1, idDenuncia);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Denuncia denuncia = new Denuncia(
                        resultSet.getLong("ID_DENUNCIA"),
                        resultSet.getLong("ID_PESSOA"),
                        resultSet.getString("DATA_DENUNCIA"),
                        resultSet.getString("DESCRICAO"),
                        resultSet.getString("STATUS"),
                        resultSet.getLong("TIPO_DENUNCIA_ID"),
                        resultSet.getLong("ID_PUBLICACAO"),
                        resultSet.getLong("ID_COMENTARIO"),
                        resultSet.getLong("ID_CATEGORIA"));
                return denuncia;
            }

            return null;
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public ResponseEntity<Response> salvarDenuncia(Denuncia denuncia) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(getURL(), getUSERNAME(), getPASSWORD());

            String sql = "INSERT INTO denuncia (ID_PESSOA, DATA_DENUNCIA, DESCRICAO, STATUS, TIPO_DENUNCIA_ID, ID_PUBLICACAO, ID_COMENTARIO, ID_CATEGORIA) "
                    + "VALUES (?, SYSDATE, ?, ?, ?, ?, ?, ?)";

            statement = connection.prepareStatement(sql);
            statement.setLong(1, denuncia.getIdPessoa());
            statement.setString(2, denuncia.getDescricao());
            statement.setString(3, denuncia.getStatus());
            statement.setLong(4, denuncia.getTipoDenunciaId());
            statement.setLong(5, denuncia.getIdPublicacao());
            if (denuncia.getIdComentario() != null) {
                statement.setLong(6, denuncia.getIdComentario());
            } else {
                statement.setNull(6, Types.BIGINT);
            }

            if (denuncia.getIdCategoria() != null) {
                statement.setLong(7, denuncia.getIdCategoria());
            } else {
                statement.setNull(7, Types.BIGINT);
            }

            statement.execute();

            return Response.ok("Sucesso ao cadastrar denúncia!");
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public ResponseEntity<Response> atualizarDenuncia(Denuncia denuncia) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(getURL(), getUSERNAME(), getPASSWORD());

            String sql = "UPDATE denuncia "
                    + "SET ID_PESSOA = ?, DATA_DENUNCIA = ?, DESCRICAO = ?, STATUS = ?, TIPO_DENUNCIA_ID = ?, ID_PUBLICACAO = ?, ID_COMENTARIO = ?, ID_CATEGORIA = ? "
                    + "WHERE ID_DENUNCIA = ?";

            statement = connection.prepareStatement(sql);
            statement.setLong(1, denuncia.getIdPessoa());
            statement.setString(2, denuncia.getDataDenuncia());
            statement.setString(3, denuncia.getDescricao());
            statement.setString(4, denuncia.getStatus());
            statement.setLong(5, denuncia.getTipoDenunciaId());
            statement.setLong(6, denuncia.getIdPublicacao());
            statement.setLong(7, denuncia.getIdComentario());
            statement.setLong(8, denuncia.getIdCategoria());
            statement.setLong(9, denuncia.getIdDenuncia());

            statement.execute();

            return Response.ok("Sucesso ao atualizar denúncia!");
        } catch (SQLException e) {
            return Response.invalid("Erro ao atualizar denúncia: " + e.getMessage());
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
