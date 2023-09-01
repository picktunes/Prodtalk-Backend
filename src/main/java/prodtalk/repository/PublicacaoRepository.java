package prodtalk.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import prodtalk.entity.Publicacao;

@Repository
public class PublicacaoRepository extends GenericRepository {
    
    public List<Publicacao> buscarPublicacoesSelecionadas(int page, int pageSize) {
        List<Publicacao> publicacoes = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(getURL(), getUSERNAME(), getPASSWORD());
             
            int offset = (page - 1) * pageSize;
            String query = "SELECT * FROM (SELECT a.*, ROWNUM rnum FROM (SELECT * FROM publicacao) a WHERE ROWNUM <= ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, pageSize);
            //statement.setInt(2, offset);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Publicacao publicacao = new Publicacao();
                publicacao.setIdPublicacao(resultSet.getInt("ID_PUBLICACAO"));
                publicacao.setDataCriacao(resultSet.getDate("DT_CRIACAO"));
                publicacao.setDataAtualizacao(resultSet.getDate("DT_ATUALIZACAO"));
                publicacao.setConteudo(resultSet.getString("CONTEUDO"));
                publicacao.setQuantidadeLikes(resultSet.getInt("QT_LIKES"));
                publicacao.setIdPessoa(resultSet.getInt("ID_PESSOA"));
                publicacao.setTitulo(resultSet.getString("DS_TITULO"));
                publicacoes.add(publicacao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return publicacoes;
    }
    
    public List<Publicacao> salvarPublicacao(Publicacao publicacao) {
        return null;
    }
    
}