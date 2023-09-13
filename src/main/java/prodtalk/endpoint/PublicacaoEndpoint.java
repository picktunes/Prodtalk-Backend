package prodtalk.endpoint;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Publicacao> buscarPublicacoes(@RequestParam(defaultValue = "1") int page) throws Exception {
        int pageSize = 10; // Número de publicações por página
        int offset = (page - 1) * pageSize;
        List<Publicacao> publicacoes = publicacaoRepository.buscarPublicacoesSelecionadas(offset, pageSize);
        return publicacoes;
    }

    @PostMapping
    public ResponseEntity<?> publicarPublicacao(@RequestBody Publicacao publicacao) {
        try {
            return ResponseEntity.ok(publicacaoRepository.salvarPublicacao(publicacao));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao salvar a publicação. " + e.getMessage());
        }
    }
}
