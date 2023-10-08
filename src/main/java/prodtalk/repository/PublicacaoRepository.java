package prodtalk.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import prodtalk.entity.Pessoa;
import prodtalk.entity.Publicacao;
import prodtalk.entity.PublicacaoCurtida;
import utils.http.Response;

@Repository
public class PublicacaoRepository extends GenericRepository {

    public List<Publicacao> buscarPublicacoesSelecionadas(int page, int pageSize) throws Exception {
        List<Publicacao> publicacoes = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(getURL(), getUSERNAME(), getPASSWORD());

            int offset = page;
            int upperLimit = page - 10;

            String query = "SELECT * FROM ("
                    + "SELECT a.*, ROWNUM rnum FROM ("
                    + "SELECT * FROM publicacao ORDER BY id_publicacao DESC"
                    + ") a WHERE ROWNUM <= ?"
                    + ") WHERE rnum > ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, offset);
            statement.setInt(2, upperLimit);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Pessoa pessoa = instanciarPessoa(resultSet);
                List<PublicacaoCurtida> publicacaoCurtida = instanciarPublicacaoCurtidas(resultSet);
                List<Map<String, Object>> comentarios = instanciarComentarios(resultSet);

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
                        publicacaoCurtida
                        );
                publicacoes.add(publicacao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return publicacoes;
    }

    public ResponseEntity<Response> salvarPublicacao(Publicacao publicacao) throws SQLException, Exception {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(getURL(), getUSERNAME(), getPASSWORD());

            String sql = "INSERT INTO PUBLICACAO (ID_PUBLICACAO, DT_CRIACAO, DT_ATUALIZACAO, CONTEUDO, QT_LIKES, ID_PESSOA, DS_TITULO, IMG)\n"
                    + "VALUES (SEQ_PUBLICACAO.NEXTVAL, SYSDATE, SYSDATE, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, publicacao.getConteudo());
            statement.setInt(2, 0);
            statement.setInt(3, 1);
            statement.setString(4, publicacao.getTitulo());
            statement.setBlob(5, stringToBlob(publicacao.getImg(), connection));

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

}
