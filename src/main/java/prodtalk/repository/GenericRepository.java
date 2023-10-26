package prodtalk.repository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;
import prodtalk.entity.Categoria;
import prodtalk.entity.Comentario;
import prodtalk.entity.Pessoa;
import prodtalk.entity.PublicacaoCurtida;
import utils.http.BancoDadosConfig;
import utils.http.BlobUtils;

@CrossOrigin("*")
public class GenericRepository {

    private static GenericRepository instance;
    private String URL;
    private String USERNAME;
    private String PASSWORD;
    private BlobUtils BlobUtils;

    public GenericRepository() {
        BancoDadosConfig bdc = new BancoDadosConfig();
        this.setURL(bdc.getUrl());
        this.setUSERNAME(bdc.getUsername());
        this.setPASSWORD(bdc.getPassword());
        this.BlobUtils = new BlobUtils();
    }

    public static GenericRepository getInstance() {
        if (instance == null) {
            instance = new GenericRepository();
        }
        return instance;
    }

    public String getURL() {
        return URL;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public String setURL(String url) {
        return this.URL = url;
    }

    public String setUSERNAME(String username) {
        return this.USERNAME = username;
    }

    public String setPASSWORD(String password) {
        return this.PASSWORD = password;
    }

    public static String blobToString(Blob blob) throws SQLException, IOException {
        if (blob == null) {
            return "";
        }

        try (InputStream is = blob.getBinaryStream(); ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            return baos.toString("UTF-8");
        }
    }

    protected Blob stringToBlob(String data, Connection connection) throws Exception {
        if (data == null) {
            return null;
        }

        byte[] bytes = data.getBytes(StandardCharsets.UTF_8);
        Blob blob = connection.createBlob();
        blob.setBytes(1, bytes);
        return blob;
    }
    
    protected Pessoa instanciarPessoa(ResultSet resultSet) throws SQLException, IOException {
        PessoaRepository p = new PessoaRepository();
        return p.getPessoaId(resultSet.getInt("ID_PESSOA"));
    }
    
    protected List<PublicacaoCurtida> instanciarPublicacaoCurtidas(ResultSet resultSet) throws SQLException, Exception {
        PublicacaoCurtidaRepository p = new PublicacaoCurtidaRepository();
        return p.buscarPublicacaoCurtidaPorPublicacao(resultSet.getLong("ID_PUBLICACAO"));
    }

    protected List<Map<String, Object>> instanciarComentarios(ResultSet resultSet) throws SQLException, Exception {
        ComentarioRepository c = new ComentarioRepository();
        return c.buscarComentariosPorPublicacao(resultSet.getLong("ID_PUBLICACAO"));
    }
    
    protected Categoria instanciarCategoria(ResultSet resultSet) throws SQLException, Exception {
        CategoriaRepository c = new CategoriaRepository();
        return c.buscarCategoria(resultSet.getLong("ID_CATEGORIA"));
    }

}
