package prodtalk.endpoint;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import prodtalk.entity.Publicacao;
import prodtalk.repository.PublicacaoRepository;

@RestController
@RequestMapping("/publicacoes")
@CrossOrigin("*")
public class PublicacaoEndpoint {

    private final PublicacaoRepository publicacaoRepository;

    @Autowired
    public PublicacaoEndpoint(PublicacaoRepository publicacaoRepository) {
        this.publicacaoRepository = publicacaoRepository;
    }

    @GetMapping
    public List<Publicacao> buscarPublicacoes(@RequestParam(defaultValue = "1") int page,
            @RequestParam(required = false) Integer idCategoria) throws Exception {
        int pageSize = 10;
        int offset = page * 10;
        List<Publicacao> publicacoes = publicacaoRepository.buscarPublicacoesSelecionadas(offset, pageSize, idCategoria);
        return publicacoes;
    }

    @GetMapping("/buscar-publicacao")
    public ResponseEntity<?> buscarPublicacaoPorID(@RequestParam long idPublicacao) {
        try {
            Publicacao publicacao = publicacaoRepository.buscarPublicacaoPorID(idPublicacao);
            return ResponseEntity.ok(publicacao);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao buscar a publica��o. " + e.getMessage());
        }
    }

    @GetMapping("/buscar-publicacoes-favoritas")
    public ResponseEntity<?> buscarPublicacoesFavoritas(@RequestParam(defaultValue = "1") int page,
            @RequestParam long idPessoa) {
        try {
            int pageSize = 10;
            int offset = page * 10;
            List<Publicacao> publicacoes = publicacaoRepository.buscarPublicacoesFavoritas(offset, pageSize, idPessoa);
            return ResponseEntity.ok(publicacoes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar as publica��es favoritas. " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> publicarPublicacao(@RequestBody Publicacao publicacao) {
        try {
            return ResponseEntity.ok(publicacaoRepository.salvarPublicacao(publicacao));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao salvar a publica��o. " + e.getMessage());
        }
    }
}
