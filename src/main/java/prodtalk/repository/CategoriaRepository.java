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

@Repository
public class CategoriaRepository extends GenericRepository {

    public Categoria buscarCategoria(Long id) throws SQLException, Exception {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(getURL(), getUSERNAME(), getPASSWORD());

            String sql = "SELECT ID_CATEGORIA, DS_NOME, DS_DESCRICAO, IMG, IMG_CAPA, DT_CRIACAO, DT_ATUALIZACAO, IE_STATUS, ID_CATEGORIA_PAI, "
                    + "ID_PESSOA_AUTOR, CONTADOR_VISUALIZACOES,     (\n" +
                        "        SELECT COUNT(*) FROM pessoa_categoria\n" +
                        "        WHERE pessoa_categoria.ID_CATEGORIA = categoria.ID_CATEGORIA\n" +
                        "    ) AS COUNT_SEGUIDORES, \n" +
                        "    (\n" +
                        "        SELECT COUNT(*) FROM publicacao\n" +
                        "        WHERE publicacao.ID_CATEGORIA = categoria.ID_CATEGORIA\n" +
                        "    ) AS COUNT_PUBLICACOES FROM categoria WHERE ID_CATEGORIA = ?";
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

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
                        resultSet.getLong("COUNT_SEGUIDORES"),
                        resultSet.getLong("COUNT_PUBLICACOES")
                );
                return categoria;
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

    public List<Categoria> buscarTodasCategorias() throws SQLException, IOException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(getURL(), getUSERNAME(), getPASSWORD());

            String sql = "SELECT ID_CATEGORIA, DS_NOME, DS_DESCRICAO, IMG, IMG_CAPA, DT_CRIACAO, DT_ATUALIZACAO, IE_STATUS, ID_CATEGORIA_PAI, ID_PESSOA_AUTOR, CONTADOR_VISUALIZACOES FROM categoria";
            statement = connection.prepareStatement(sql);

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

    public ResponseEntity<Response> salvarCategoria(Categoria categoria) throws SQLException, Exception {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(getURL(), getUSERNAME(), getPASSWORD());

            String sql = "INSERT INTO categoria (ID_CATEGORIA, DS_NOME, DS_DESCRICAO, IMG, IMG_CAPA, DT_CRIACAO, DT_ATUALIZACAO, IE_STATUS,"
                    + " ID_CATEGORIA_PAI, ID_PESSOA_AUTOR, CONTADOR_VISUALIZACOES) VALUES (CATEGORIA_SEQ.NEXTVAL, ?, ?, ?, ?, sysdate, sysdate,  ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, categoria.getDsNome());
            statement.setString(2, categoria.getDsDescricao());
            statement.setBlob(3, stringToBlob(categoria.getImg(), connection));
            statement.setBlob(4, stringToBlob(categoria.getImgCapa(), connection));
            statement.setString(5, categoria.getIeStatus());
            setLongOrNull(statement, 6, categoria.getIdCategoriaPai());
            setLongOrNull(statement, 7, categoria.getIdPessoaAutor());
            setLongOrNull(statement, 8, categoria.getContadorVisualizacoes());

            statement.execute();

            return Response.ok("Categoria cadastrada com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.invalid("Erro ao inserir categoria: " + e.getMessage()); // Ajuste a mensagem de erro conforme necessário
        } catch (Exception e) {
            e.printStackTrace();
            return Response.invalid("Erro ao inserir categoria: " + e.getMessage()); // Ajuste a mensagem de erro conforme necessário
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public ResponseEntity<Response> alterarCategoria(Categoria categoria) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(getURL(), getUSERNAME(), getPASSWORD());

            String sql = "UPDATE categoria SET DS_NOME = ?, DS_DESCRICAO = ?, IMG = ?, IMG_CAPA = ?, DT_ATUALIZACAO = ?, IE_STATUS = ?, ID_CATEGORIA_PAI = ?, ID_PESSOA_AUTOR = ?, CONTADOR_VISUALIZACOES = ? WHERE ID_CATEGORIA = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, categoria.getDsNome());
            statement.setString(2, categoria.getDsDescricao());
            // Defina os parâmetros do Blob (IMG e IMG_CAPA) conforme necessário
            statement.setTimestamp(5, categoria.getDtAtualizacao());
            statement.setString(6, categoria.getIeStatus());
            statement.setLong(7, categoria.getIdCategoriaPai());
            statement.setLong(8, categoria.getIdPessoaAutor());
            statement.setLong(9, categoria.getContadorVisualizacoes());
            statement.setLong(10, categoria.getIdCategoria());

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                return Response.ok("Dados da categoria atualizados com sucesso!");
            } else {
                return Response.invalid("Falha ao atualizar os dados. Categoria não encontrada.");
            }
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public ResponseEntity<Response> deletarCategoria(Long id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(getURL(), getUSERNAME(), getPASSWORD());

            String sql = "DELETE FROM categoria WHERE ID_CATEGORIA = ?";
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                return Response.ok("Categoria excluída com sucesso!");
            } else {
                return Response.invalid("Falha ao excluir a categoria. Categoria não encontrada.");
            }
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
