package prodtalk.endpoint;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
            return ResponseEntity.internalServerError().body("Erro ao buscar a publicação. " + e.getMessage());
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
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar as publicações favoritas. " + e.getMessage());
        }
    }
    
    @GetMapping("/buscar-publicacoes-pessoa")
    public ResponseEntity<?> buscarPublicacoesPessoa(@RequestParam long idPessoa) {
        try {
            List<Publicacao> publicacoes = publicacaoRepository.buscarPublicacoesPessoa(idPessoa);
            return ResponseEntity.ok(publicacoes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar as publicações favoritas. " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> publicarPublicacao(@RequestBody Publicacao publicacao) {
        try {
            return ResponseEntity.ok(publicacaoRepository.salvarPublicacao(publicacao));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao salvar a publicação. " + e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deletePublicacao(@RequestParam long idPublicacao) {
        try {
            Publicacao publicacao = publicacaoRepository.buscarPublicacaoPorID(idPublicacao);
            if (publicacao != null) {
                publicacaoRepository.deletePublicacao(idPublicacao);
                return ResponseEntity.ok("Publicação excluída com sucesso!");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A publicação não foi encontrada.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir a publicação. " + e.getMessage());
        }
    }
    
    @PutMapping("/publicacao")
    public ResponseEntity<?> atualizarPublicacao(@RequestBody Publicacao publicacao) {
        try {
            Publicacao publicacaoExistente = publicacaoRepository.buscarPublicacaoPorID(publicacao.getIdPublicacao());

            if (publicacaoExistente != null) {
                publicacaoExistente.setConteudo(publicacao.getConteudo());
                publicacaoExistente.setTitulo(publicacao.getTitulo());
                publicacaoExistente.setImg(publicacao.getImg());
                publicacaoExistente.setCategoria(publicacao.getCategoria());

                publicacaoRepository.atualizarPublicacao(publicacao.getIdPublicacao(), publicacaoExistente);

                return ResponseEntity.ok("Publicação atualizada com sucesso!");
            } else {
                return ResponseEntity.internalServerError().body("A publicação não foi encontrada.");
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao atualizar a publicação. " + e.getMessage());
        }
    }

}
