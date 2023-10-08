package prodtalk.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import prodtalk.entity.PublicacaoCurtida;
import prodtalk.repository.PublicacaoCurtidaRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import prodtalk.entity.Pessoa;
import prodtalk.entity.Publicacao;

@RestController
@RequestMapping("/publicacao-curtida")
@CrossOrigin("*")
public class PublicacaoCurtidaEndpoint {

    private final PublicacaoCurtidaRepository publicacaoCurtidaRepository;

    @Autowired
    public PublicacaoCurtidaEndpoint(PublicacaoCurtidaRepository publicacaoCurtidaRepository) {
        this.publicacaoCurtidaRepository = publicacaoCurtidaRepository;
    }

    @GetMapping
    public ResponseEntity<List<PublicacaoCurtida>> getCurtidasByPublicacaoId(@RequestParam int idPublicacao) {
        try {
            List<PublicacaoCurtida> curtidas = publicacaoCurtidaRepository.buscarPublicacaoCurtidaPorPublicacao(idPublicacao);
            if (curtidas.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(curtidas);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @PostMapping
    public ResponseEntity<?> curtirPublicacao(@RequestBody Map<String, Object> request) {
        try {
            Long idPublicacao = Long.parseLong(request.get("idPublicacao").toString());
            Long idPessoa = Long.parseLong(request.get("idPessoa").toString());
            
            Optional<PublicacaoCurtida> curtidaExistente = publicacaoCurtidaRepository.findByPublicacaoAndPessoa(
                    idPublicacao, 
                    idPessoa
            );

            if (curtidaExistente.isPresent()) {
                return ResponseEntity.ok().body(publicacaoCurtidaRepository.delete(idPublicacao, idPessoa));
            } else {
                return ResponseEntity.ok(publicacaoCurtidaRepository.save( idPublicacao, idPessoa));
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao curtir/descurtir a publicação. " + e.getMessage());
        }
    }
}
