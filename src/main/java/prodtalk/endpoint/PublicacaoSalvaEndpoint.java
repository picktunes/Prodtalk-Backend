package prodtalk.endpoint;

import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prodtalk.entity.PublicacaoSalva;
import prodtalk.repository.PublicacaoSalvaRepository;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/publicacoes-salvas")
@CrossOrigin("*")
public class PublicacaoSalvaEndpoint {

    private final PublicacaoSalvaRepository publicacaoSalvaRepository;

    @Autowired
    public PublicacaoSalvaEndpoint(PublicacaoSalvaRepository publicacaoSalvaRepository) {
        this.publicacaoSalvaRepository = publicacaoSalvaRepository;
    }

    @PostMapping
    public ResponseEntity<?> inserirPublicacaoSalva(@RequestBody Map<String, Long> requestBody) {
        Long idPessoa = requestBody.get("idPessoa");
        Long idPublicacao = requestBody.get("idPublicacao");

        if (idPessoa == null || idPublicacao == null) {
            return ResponseEntity.badRequest().body("Os campos 'idPessoa' e 'idPublicacao' são obrigatórios no corpo da solicitação.");
        }

        try {
            publicacaoSalvaRepository.inserirPublicacaoSalva(idPessoa, idPublicacao);
            return ResponseEntity.ok("Publicação salva com sucesso!");
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().body("Erro ao salvar a publicação. " + e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> excluirPublicacaoSalva(@RequestParam("idPessoa") Long idPessoa, @RequestParam("idPublicacao") Long idPublicacao) {
        if (idPessoa == null || idPublicacao == null) {
            return ResponseEntity.badRequest().body("Os campos 'idPessoa' e 'idPublicacao' são obrigatórios na URL.");
        }

        try {
            publicacaoSalvaRepository.excluirPublicacaoSalva(idPessoa, idPublicacao);
            return ResponseEntity.ok("Publicação salva removida!");
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().body("Erro ao remover a publicação salva. " + e.getMessage());
        }
    }

    @GetMapping("/{idPublicacaoSalva}")
    public ResponseEntity<?> buscarPublicacaoSalvaPorID(@PathVariable long idPublicacaoSalva) {
        try {
            PublicacaoSalva publicacaoSalva = publicacaoSalvaRepository.buscarPublicacaoSalvaPorID(idPublicacaoSalva);
            if (publicacaoSalva != null) {
                return ResponseEntity.ok(publicacaoSalva);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().body("Erro ao buscar a publicação salva. " + e.getMessage());
        }
    }

    @PutMapping("/{idPublicacaoSalva}")
    public ResponseEntity<?> atualizarPublicacaoSalva(@PathVariable long idPublicacaoSalva, @RequestBody PublicacaoSalva publicacaoSalva) {
        try {
            publicacaoSalva.setIdPublicacaoSalva(idPublicacaoSalva);
            publicacaoSalvaRepository.atualizarPublicacaoSalva(publicacaoSalva);
            return ResponseEntity.ok("Publicação salva atualizada com sucesso!");
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().body("Erro ao atualizar a publicação salva. " + e.getMessage());
        }
    }

    @GetMapping("/pessoa")
    public ResponseEntity<?> buscarPublicacoesSalvasPorPessoa(@RequestParam long idPessoa) {
        try {
            List<PublicacaoSalva> publicacoesSalvas = publicacaoSalvaRepository.buscarPublicacoesSalvasPorPessoa(idPessoa);
            return ResponseEntity.ok(publicacoesSalvas);
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().body("Erro ao buscar as publicações salvas da pessoa. " + e.getMessage());
        }
    }
}
