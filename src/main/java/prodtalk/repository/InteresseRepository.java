package prodtalk.repository;
    
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import prodtalk.entity.Interesse;

@Repository
public class InteresseRepository extends GenericRepository {
    
    public List<Interesse> buscarInteresses(int page, int pageSize) {
        List<Interesse> interesses = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(getURL(), getUSERNAME(), getPASSWORD());
             
            int offset = (page - 1) * pageSize;
            String query = "SELECT * FROM (SELECT a.*, ROWNUM rnum FROM (SELECT * FROM interesse) a WHERE ROWNUM <= ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, pageSize);
            //statement.setInt(2, offset);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Interesse interesse = new Interesse(0,null,null,null,null);
                interesse.setIdInteresse(resultSet.getInt("ID_INTERESSE"));
                interesse.setDsInteresse(resultSet.getString("DS_INTERESSE"));
                interesse.setDtCriacao(resultSet.getDate("DT_INCLUSAO"));
                interesse.setDtAtualizacao(resultSet.getDate("DT_ALTERACAO"));
                interesse.setIeAtivo(resultSet.getString("IE_ATIVO"));
                interesses.add(interesse);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return interesses;
    }
    
}