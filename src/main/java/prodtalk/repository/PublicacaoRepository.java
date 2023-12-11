package prodtalk.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import prodtalk.entity.Categoria;
import prodtalk.entity.Pessoa;
import prodtalk.entity.Publicacao;
import prodtalk.entity.PublicacaoCurtida;
import prodtalk.repository.PublicacaoSalvaRepository;
import utils.http.Response;

@Repository
public class PublicacaoRepository extends GenericRepository {

    public List<Publicacao> buscarPublicacoesSelecionadas(int page, int pageSize, Integer idCategoria) throws Exception {
        List<Publicacao> publicacoes = new ArrayList<>();
        PublicacaoSalvaRepository publicacaoSalvaRepository = new PublicacaoSalvaRepository();
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(getURL(), getUSERNAME(), getPASSWORD());

            int offset = page;
            int upperLimit = page - 10;

            String query = "SELECT * FROM ("
                    + "SELECT a.*, ROWNUM rnum FROM ("
                    + "SELECT * FROM publicacao";

            if (idCategoria != null) {
                query += " WHERE ID_CATEGORIA = ? ";
            }

            query += " ORDER BY id_publicacao DESC"
                    + ") a WHERE ROWNUM <= ?"
                    + ") WHERE rnum > ?";

            statement = connection.prepareStatement(query);

            int parameterIndex = 1;

            if (idCategoria != null) {
                statement.setInt(parameterIndex, idCategoria);
                parameterIndex++;
            }

            statement.setInt(parameterIndex, offset);
            statement.setInt(parameterIndex + 1, upperLimit);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Pessoa pessoa = instanciarPessoa(resultSet);
                List<PublicacaoCurtida> publicacaoCurtida = instanciarPublicacaoCurtidas(resultSet);
                List<Map<String, Object>> comentarios = instanciarComentarios(resultSet);
                Categoria categoria = instanciarCategoria(resultSet);

                Publicacao publicacao = new Publicacao(
                        pessoa,
                        resultSet.getInt("ID_PUBLICACAO"),
                        resultSet.getInt("ID_PESSOA"),
                        resultSet.getDate("DT_CRIACAO"),
                        resultSet.getDate("DT_ATUALIZACAO"),
                        resultSet.getString("CONTEUDO"),
                        resultSet.getString("DS_TITULO"),
                        comentarios,
                        blobToString(resultSet.getBlob("IMG")),
                        publicacaoCurtida,
                        categoria,
                        publicacaoSalvaRepository.verificarPublicacaoSalva(resultSet.getInt("ID_PESSOA"), resultSet.getInt("ID_PUBLICACAO")
                        )
                );
                publicacoes.add(publicacao);
            }
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
        return publicacoes;
    }

    public Publicacao buscarPublicacaoPorID(long publicacaoID) throws Exception {
        Publicacao publicacao = null;
        PublicacaoSalvaRepository publicacaoSalvaRepository = new PublicacaoSalvaRepository();
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(getURL(), getUSERNAME(), getPASSWORD());

            String query = "SELECT * FROM publicacao WHERE ID_PUBLICACAO = ?";
            statement = connection.prepareStatement(query);
            statement.setLong(1, publicacaoID);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Pessoa pessoa = instanciarPessoa(resultSet);
                List<PublicacaoCurtida> publicacaoCurtida = instanciarPublicacaoCurtidas(resultSet);
                List<Map<String, Object>> comentarios = instanciarComentarios(resultSet);
                Categoria categoria = instanciarCategoria(resultSet);

                publicacao = new Publicacao(
                        pessoa,
                        resultSet.getInt("ID_PUBLICACAO"),
                        resultSet.getInt("ID_PESSOA"),
                        resultSet.getDate("DT_CRIACAO"),
                        resultSet.getDate("DT_ATUALIZACAO"),
                        resultSet.getString("CONTEUDO"),
                        resultSet.getString("DS_TITULO"),
                        comentarios,
                        blobToString(resultSet.getBlob("IMG")),
                        publicacaoCurtida,
                        categoria,
                        publicacaoSalvaRepository.verificarPublicacaoSalva(resultSet.getInt("ID_PESSOA"), resultSet.getInt("ID_PUBLICACAO")
                        )
                );
            }
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
        return publicacao;
    }

    public List<Publicacao> buscarPublicacoesFavoritas(int page, int pageSize, long idPessoa) throws Exception {
        List<Publicacao> publicacoesFavoritas = new ArrayList<>();
        PublicacaoSalvaRepository publicacaoSalvaRepository = new PublicacaoSalvaRepository();
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(getURL(), getUSERNAME(), getPASSWORD());
            int upperLimit = page - 10;

            String query = "SELECT * FROM ("
                    + "SELECT p.*, ROWNUM AS rnum FROM ("
                    + "SELECT p.* FROM publicacao p "
                    + "INNER JOIN publicacao_salva ps ON p.ID_PUBLICACAO = ps.ID_PUBLICACAO "
                    + "WHERE ps.ID_PESSOA = ? "
                    + "ORDER BY p.ID_PUBLICACAO DESC) p "
                    + "WHERE ROWNUM <= ?"
                    + ") "
                    + "WHERE rnum > ?";

            statement = connection.prepareStatement(query);
            statement.setLong(1, idPessoa);
            statement.setInt(2, page);
            statement.setInt(3, upperLimit);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Pessoa pessoa = instanciarPessoa(resultSet);
                List<PublicacaoCurtida> publicacaoCurtida = instanciarPublicacaoCurtidas(resultSet);
                List<Map<String, Object>> comentarios = instanciarComentarios(resultSet);
                Categoria categoria = instanciarCategoria(resultSet);

                Publicacao publicacao = new Publicacao(
                        pessoa,
                        resultSet.getInt("ID_PUBLICACAO"),
                        resultSet.getInt("ID_PESSOA"),
                        resultSet.getDate("DT_CRIACAO"),
                        resultSet.getDate("DT_ATUALIZACAO"),
                        resultSet.getString("CONTEUDO"),
                        resultSet.getString("DS_TITULO"),
                        comentarios,
                        blobToString(resultSet.getBlob("IMG")),
                        publicacaoCurtida,
                        categoria,
                        publicacaoSalvaRepository.verificarPublicacaoSalva(resultSet.getInt("ID_PESSOA"), resultSet.getInt("ID_PUBLICACAO"))
                );

                publicacoesFavoritas.add(publicacao);
            }
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
        return publicacoesFavoritas;
    }

    public List<Publicacao> buscarPublicacoesPessoa(long idPessoa) throws Exception {
        List<Publicacao> publicacoesFavoritas = new ArrayList<>();
        PublicacaoSalvaRepository publicacaoSalvaRepository = new PublicacaoSalvaRepository();
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(getURL(), getUSERNAME(), getPASSWORD());

            String query = "SELECT * FROM publicacao "
                    + "WHERE ID_PESSOA = ? "
                    + "ORDER BY ID_PUBLICACAO DESC";

            statement = connection.prepareStatement(query);
            statement.setLong(1, idPessoa);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Pessoa pessoa = instanciarPessoa(resultSet);
                List<PublicacaoCurtida> publicacaoCurtida = instanciarPublicacaoCurtidas(resultSet);
                List<Map<String, Object>> comentarios = instanciarComentarios(resultSet);
                Categoria categoria = instanciarCategoria(resultSet);

                Publicacao publicacao = new Publicacao(
                        pessoa,
                        resultSet.getInt("ID_PUBLICACAO"),
                        resultSet.getInt("ID_PESSOA"),
                        resultSet.getDate("DT_CRIACAO"),
                        resultSet.getDate("DT_ATUALIZACAO"),
                        resultSet.getString("CONTEUDO"),
                        resultSet.getString("DS_TITULO"),
                        comentarios,
                        blobToString(resultSet.getBlob("IMG")),
                        publicacaoCurtida,
                        categoria,
                        publicacaoSalvaRepository.verificarPublicacaoSalva(resultSet.getInt("ID_PESSOA"), resultSet.getInt("ID_PUBLICACAO"))
                );

                publicacoesFavoritas.add(publicacao);
            }
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
        return publicacoesFavoritas;
    }

    public List<Publicacao> buscarPublicacoesPorTexto(String texto, int page, int pageSize) throws Exception {
        List<Publicacao> publicacoes = new ArrayList<>();
        PublicacaoSalvaRepository publicacaoSalvaRepository = new PublicacaoSalvaRepository();
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(getURL(), getUSERNAME(), getPASSWORD());

            int offset = page;
            int upperLimit = page - 10;

            String query = "SELECT * FROM ("
                    + "SELECT a.*, ROWNUM rnum FROM ("
                    + "SELECT * FROM publicacao WHERE CONTAINS(CONTEUDO, ?, 1) > 0 OR CONTAINS(DS_TITULO, ?, 2) > 0 ORDER BY id_publicacao DESC"
                    + ") a WHERE ROWNUM <= ?"
                    + ") WHERE rnum >= ?";

            statement = connection.prepareStatement(query);
            statement.setString(1, "%" + texto.toUpperCase() + "%");
            statement.setString(2, "%" + texto.toUpperCase() + "%");
            statement.setInt(3, offset);
            statement.setInt(4, upperLimit);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Pessoa pessoa = instanciarPessoa(resultSet);
                List<PublicacaoCurtida> publicacaoCurtida = instanciarPublicacaoCurtidas(resultSet);
                List<Map<String, Object>> comentarios = instanciarComentarios(resultSet);
                Categoria categoria = instanciarCategoria(resultSet);

                Publicacao publicacao = new Publicacao(
                        pessoa,
                        resultSet.getInt("ID_PUBLICACAO"),
                        resultSet.getInt("ID_PESSOA"),
                        resultSet.getDate("DT_CRIACAO"),
                        resultSet.getDate("DT_ATUALIZACAO"),
                        resultSet.getString("CONTEUDO"),
                        resultSet.getString("DS_TITULO"),
                        comentarios,
                        blobToString(resultSet.getBlob("IMG")),
                        publicacaoCurtida,
                        categoria,
                        publicacaoSalvaRepository.verificarPublicacaoSalva(resultSet.getInt("ID_PESSOA"), resultSet.getInt("ID_PUBLICACAO"))
                );
                publicacoes.add(publicacao);
            }
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
        return publicacoes;
    }

    public ResponseEntity<Response> salvarPublicacao(Publicacao publicacao) throws SQLException, Exception {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(getURL(), getUSERNAME(), getPASSWORD());

            String sql = "INSERT INTO PUBLICACAO (ID_PUBLICACAO, DT_CRIACAO, DT_ATUALIZACAO, CONTEUDO, QT_LIKES, ID_PESSOA, DS_TITULO, IMG, ID_CATEGORIA)\n"
                    + "VALUES (SEQ_PUBLICACAO.NEXTVAL, SYSDATE, SYSDATE, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, publicacao.getConteudo());
            statement.setInt(2, 0);
            statement.setInt(3, publicacao.getIdPessoa());
            statement.setString(4, publicacao.getTitulo());
            statement.setBlob(5, stringToBlob(publicacao.getImg(), connection));
            statement.setObject(6, returnIfNotNull(publicacao, null)
                    .map(Publicacao::getCategoria)
                    .map(Categoria::getIdCategoria)
                    .orElse(null));
            statement.execute();

            return Response.ok("Sucesso ao cadastrar a publicação!");
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public ResponseEntity<Response> deletePublicacao(long idPublicacao) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(getURL(), getUSERNAME(), getPASSWORD());

            String sql = "DELETE FROM PUBLICACAO WHERE ID_PUBLICACAO = ?";
            statement = connection.prepareStatement(sql);
            statement.setLong(1, idPublicacao);

            statement.execute();
        } catch (SQLException e) {
            return Response.invalid("Erro ao excluir a publicação: " + e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return Response.ok("Publicação excluída com sucesso!");
    }

    public ResponseEntity<Response> atualizarPublicacao(long idPublicacao, Publicacao publicacao) throws SQLException, Exception {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(getURL(), getUSERNAME(), getPASSWORD());

            String sql = "UPDATE PUBLICACAO SET CONTEUDO = ?, DS_TITULO = ?, IMG = ?, ID_CATEGORIA = ? WHERE ID_PUBLICACAO = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, publicacao.getConteudo());
            statement.setString(2, publicacao.getTitulo());
            statement.setBlob(3, stringToBlob(publicacao.getImg(), connection));
            statement.setObject(4, returnIfNotNull(publicacao, null)
                    .map(Publicacao::getCategoria)
                    .map(Categoria::getIdCategoria)
                    .orElse(null));
            statement.setLong(5, idPublicacao);

            statement.executeUpdate();

            return Response.ok("Sucesso ao atualizar a publicação!");
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
