package prodtalk.repository;

import entity.Cadastro;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import prodtalk.entity.Publicacao;
import utils.http.Response;

public class CadastroRepository extends GenericRepository {

    public Cadastro getCadastro(String email, String login, String senha) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(getURL(), getUSERNAME(), getPASSWORD());

            String sql = "    SELECT ID_CADASTRO, DS_EMAIL, DS_LOGIN, DS_SENHA, verificar_login(ds_email, ds_senha) STATUS_CADASTRO "
                    + "    FROM cadastro "
                    + "    WHERE ds_email = ? "
                    + "    AND ds_senha = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, senha);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Cadastro cadastro = new Cadastro(
                        resultSet.getLong("ID_CADASTRO"),
                        resultSet.getString("DS_EMAIL"),
                        resultSet.getString("DS_LOGIN"),
                        resultSet.getString("DS_SENHA"),
                        resultSet.getInt("STATUS_CADASTRO"));
                return cadastro;
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

    public ResponseEntity<Response> salvarCadastro(Cadastro cadastro) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(getURL(), getUSERNAME(), getPASSWORD());

            String sql = "call cadastrar_usuario(?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, cadastro.getEmail());
            statement.setString(2, cadastro.getLogin());
            statement.setString(3, cadastro.getSenha());

            statement.execute();

            return Response.ok("Sucesso ao cadastrar usuário!");
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /*public ResponseEntity<Response>  updateCadastro(Cadastro cadastro) throws SQLException {
        return 0;
    }

    public ResponseEntity<Response>  deleteCadastro(Cadastro cadastro) throws SQLException {
        return 0;
    }*/
}
