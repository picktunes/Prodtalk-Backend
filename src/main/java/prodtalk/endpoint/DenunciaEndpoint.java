package prodtalk.endpoint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.IOException;
import java.sql.SQLException;
import org.springframework.http.ResponseEntity;
import prodtalk.entity.Denuncia;
import org.springframework.web.bind.annotation.*;
import prodtalk.repository.DenunciaRepository;
import utils.http.Response;

@RestController
@CrossOrigin("*")
@JsonIgnoreProperties({"*", "handler"})
@RequestMapping("/denuncia")

public class DenunciaEndpoint {

    DenunciaRepository denunciaRepository = new DenunciaRepository();

    @GetMapping
    public ResponseEntity<Response> getDenuncia(@RequestParam("idDenuncia") long idDenuncia) throws IOException {
        try {
            return Response.ok(denunciaRepository.getDenuncia(idDenuncia));
        } catch (SQLException e) {
            return Response.invalid("Erro ao buscar a denúncia. " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Response> cadastrarDenuncia(@RequestBody Denuncia denuncia) {
        try {
            return Response.ok(denunciaRepository.salvarDenuncia(denuncia));
        } catch (SQLException e) {
            return Response.invalid("Erro ao cadastrar a denúncia. " + e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<Response> alterarDenuncia(@RequestBody Denuncia denuncia) throws Exception {
        try {
            return Response.ok(denunciaRepository.atualizarDenuncia(denuncia));
        } catch (SQLException e) {
            return Response.invalid("Erro ao atualizar a denúncia. " + e.getMessage());
        }
    }
}
