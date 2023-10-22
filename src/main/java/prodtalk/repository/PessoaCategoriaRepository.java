package prodtalk.repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import prodtalk.entity.Categoria;
import utils.http.Response;

@Repository
public class PessoaCategoriaRepository extends GenericRepository {

    public List<Categoria> getCategoriasDaPessoa(long idPessoa) throws SQLException, IOException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(getURL(), getUSERNAME(), getPASSWORD());

            String sql = "SELECT c.ID_CATEGORIA, c.DS_NOME, c.DS_DESCRICAO, c.IMG, c.IMG_CAPA, c.DT_CRIACAO, c.DT_ATUALIZACAO, c.IE_STATUS, c.ID_CATEGORIA_PAI, "
                    + "c.ID_PESSOA_AUTOR, c.CONTADOR_VISUALIZACOES FROM pessoa_categoria pc, categoria c "
                    + "WHERE pc.id_categoria = c.ID_CATEGORIA AND pc.id_pessoa = ?";
            statement = connection.prepareStatement(sql);
            statement.setLong(1, idPessoa);

            ResultSet resultSet = statement.executeQuery();
            List<Categoria> categorias = new ArrayList<>();

            while (resultSet.next()) {
                Categoria categoria = new Categoria(
                        resultSet.getLong("ID_CATEGORIA"),
                        resultSet.getString("DS_NOME"),
                        resultSet.getString("DS_DESCRICAO"),
                        blobToString(resultSet.getBlob("IMG")),
                        blobToString(resultSet.getBlob("IMG_CAPA")),
                        resultSet.getTimestamp("DT_CRIACAO"),
                        resultSet.getTimestamp("DT_ATUALIZACAO"),
                        resultSet.getString("IE_STATUS"),
                        resultSet.getLong("ID_CATEGORIA_PAI"),
                        resultSet.getLong("ID_PESSOA_AUTOR"),
                        resultSet.getLong("CONTADOR_VISUALIZACOES"),
                        0L,
                        0L
                );
                categorias.add(categoria);
            }

            return categorias;
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public ResponseEntity<Response> associarCategoriaAPessoa(Long idPessoa, Long idCategoria) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(getURL(), getUSERNAME(), getPASSWORD());

            String sql = "INSERT INTO pessoa_categoria (ID_PESSOA, ID_CATEGORIA, DT_CRIACAO) VALUES (?, ?, CURRENT_TIMESTAMP)";
            statement = connection.prepareStatement(sql);
            statement.setLong(1, idPessoa);
            statement.setLong(2, idCategoria);

            statement.execute();

            return Response.ok("Sucesso ao associar categoria!");
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
        return Response.invalid("Erro ao associar categoria!");
    }

    public void desassociarCategoriaDaPessoa(Long idPessoa, Long idCategoria) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(getURL(), getUSERNAME(), getPASSWORD());

            String sql = "DELETE FROM pessoa_categoria WHERE ID_PESSOA = ? AND ID_CATEGORIA = ?";
            statement = connection.prepareStatement(sql);
            statement.setLong(1, idPessoa);
            statement.setLong(2, idCategoria);

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
    }
}
