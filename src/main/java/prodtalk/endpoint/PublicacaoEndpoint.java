package prodtalk.endpoint;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Publicacao> buscarPublicacoes(@RequestParam(defaultValue = "1") int page) {
        int pageSize = 10; // Número de publicações por página
        int offset = (page - 1) * pageSize;
        List<Publicacao> publicacoes = publicacaoRepository.buscarPublicacoesSelecionadas(offset, pageSize);
        return publicacoes;
    }
    
    @PostMapping
    public List<Publicacao> publicarPublicacao(@RequestBody Publicacao publicacao){
        //try {
            return publicacaoRepository.salvarPublicacao(publicacao);
        //} catch (SQLException e) {
         //    return ResponseEntity.internalServerError().body("Erro ao `salvara publicação. " + e.getMessage());
        //}
    }
}