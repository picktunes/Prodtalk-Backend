package prodtalk.repository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import entity.Cadastro;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.http.ResponseEntity;
import prodtalk.entity.Pessoa;
import utils.http.Response;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class PessoaRepository extends GenericRepository {

    public Pessoa getPessoa(long nrSeqCadastro) throws SQLException, IOException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(getURL(), getUSERNAME(), getPASSWORD());

            String sql = " select ID_PESSOA, DS_NOME_COMPLETO, DS_SEXO, NR_IDADE, DS_PROFISSAO, DS_BIOGRAFIA, DS_INTERESSES, BLB_FOTO_PERFIL "
                    + " from pessoa "
                    + " where id_cadastro = ? ";
            statement = connection.prepareStatement(sql);
            statement.setLong(1, nrSeqCadastro);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                    Pessoa pessoa = new Pessoa(
                        resultSet.getLong("ID_PESSOA"),
                        resultSet.getString("DS_NOME_COMPLETO"),
                        resultSet.getString("DS_SEXO"),
                        resultSet.getInt("NR_IDADE"),
                        resultSet.getString("DS_PROFISSAO"),
                        blobToString(resultSet.getBlob("BLB_FOTO_PERFIL")),
                        resultSet.getString("DS_BIOGRAFIA"));
                return pessoa;
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
    
    public Pessoa getPessoaPorId(long idPessoa) throws SQLException, IOException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(getURL(), getUSERNAME(), getPASSWORD());

            String sql = " select ID_PESSOA, DS_NOME_COMPLETO, DS_SEXO, NR_IDADE, DS_PROFISSAO, DS_BIOGRAFIA, DS_INTERESSES, BLB_FOTO_PERFIL "
                    + " from pessoa "
                    + " where id_pessoa = ? ";
            statement = connection.prepareStatement(sql);
            statement.setLong(1, idPessoa);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                    Pessoa pessoa = new Pessoa(
                        resultSet.getLong("ID_PESSOA"),
                        resultSet.getString("DS_NOME_COMPLETO"),
                        resultSet.getString("DS_SEXO"),
                        resultSet.getInt("NR_IDADE"),
                        resultSet.getString("DS_PROFISSAO"),
                        blobToString(resultSet.getBlob("BLB_FOTO_PERFIL")),
                        resultSet.getString("DS_BIOGRAFIA"));
                return pessoa;
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

    public Pessoa getPessoaId(long idCadastro) throws SQLException, IOException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(getURL(), getUSERNAME(), getPASSWORD());

            String sql = " select ID_PESSOA, DS_NOME_COMPLETO, DS_SEXO, NR_IDADE, DS_PROFISSAO, DS_BIOGRAFIA, BLB_FOTO_PERFIL "
                    + " from pessoa "
                    + " where id_pessoa = ? ";
            statement = connection.prepareStatement(sql);
            statement.setLong(1, idCadastro);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Pessoa pessoa = new Pessoa(
                        resultSet.getLong("ID_PESSOA"),
                        resultSet.getString("DS_NOME_COMPLETO"),
                        resultSet.getString("DS_SEXO"),
                        resultSet.getInt("NR_IDADE"),
                        resultSet.getString("DS_PROFISSAO"),
                        blobToString(resultSet.getBlob("BLB_FOTO_PERFIL")),
                        resultSet.getString("DS_BIOGRAFIA"));
                return pessoa;
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

    public ResponseEntity<Response> salvarPessoa(Cadastro cadastro) throws SQLException {
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

            return Response.ok("Sucesso ao cadastrar usu�rio!");
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public ResponseEntity<Response> alterarPessoa(Pessoa pessoa) throws SQLException, Exception {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(getURL(), getUSERNAME(), getPASSWORD());

            String sql = "UPDATE pessoa "
                    + "SET DS_SEXO = ?, DS_PROFISSAO = ?, DS_BIOGRAFIA = ?, DS_NOME_COMPLETO = ?, BLB_FOTO_PERFIL = ? "
                    + "WHERE ID_PESSOA = ?";

            statement = connection.prepareStatement(sql);
            statement.setString(1, pessoa.getSexo());
            statement.setString(2, pessoa.getProfissao());
            statement.setString(3, pessoa.getBiografia());
            statement.setString(4, pessoa.getNomeCompleto());
            statement.setBlob(5, stringToBlob(pessoa.getFotoPerfil(), connection));
            statement.setLong(6, pessoa.getIdPessoa());

            statement.execute();

            return Response.ok("Sucesso ao atualizar usu�rio!");
        } catch (SQLException e) {
            return Response.invalid("Erro ao atualizar usu�rio: " + e.getMessage());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
