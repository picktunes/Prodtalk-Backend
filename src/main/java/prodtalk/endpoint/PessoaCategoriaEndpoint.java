package prodtalk.endpoint;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import prodtalk.entity.Categoria;
import prodtalk.repository.PessoaCategoriaRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/pessoa-categoria")
public class PessoaCategoriaEndpoint {

    private final PessoaCategoriaRepository pessoaCategoriaRepository;

    @Autowired
    public PessoaCategoriaEndpoint(PessoaCategoriaRepository pessoaCategoriaRepository) {
        this.pessoaCategoriaRepository = pessoaCategoriaRepository;
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> getCategoriasDaPessoa(@RequestParam("idPessoa") Long idPessoa) throws SQLException, IOException {
        List<Categoria> categorias = pessoaCategoriaRepository.getCategoriasDaPessoa(idPessoa);
        return ResponseEntity.ok(categorias);
    }

    @PostMapping
    public ResponseEntity<?> associarCategoriaAPessoa(@RequestBody Map<String, Long> requestBody) throws SQLException {
        Long idPessoa = requestBody.get("idPessoa");
        Long idCategoria = requestBody.get("idCategoria");

        pessoaCategoriaRepository.associarCategoriaAPessoa(idPessoa, idCategoria);
        return ResponseEntity.ok("Categoria associada com sucesso.");
    }

    @DeleteMapping
    public ResponseEntity<?> desassociarCategoriaDaPessoa(@RequestHeader("idPessoa") Long idPessoa,
            @RequestHeader("idCategoria") Long idCategoria) throws SQLException {
        pessoaCategoriaRepository.desassociarCategoriaDaPessoa(idPessoa, idCategoria);
        return ResponseEntity.ok("Categoria removida com sucesso.");
    }

}
