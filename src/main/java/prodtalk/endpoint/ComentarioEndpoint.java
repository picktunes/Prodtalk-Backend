package prodtalk.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.web.bind.annotation.RequestParam;
import prodtalk.entity.Comentario;
import prodtalk.entity.ComentarioHierarquico;
import prodtalk.repository.ComentarioRepository;

@RestController
@RequestMapping("/comentario")
@CrossOrigin("*")
public class ComentarioEndpoint {

    private final ComentarioRepository comentarioRepository;

    @Autowired
    public ComentarioEndpoint(ComentarioRepository comentarioRepository) {
        this.comentarioRepository = comentarioRepository;
    }

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> buscarComentariosPorPublicacao(@RequestParam int idPublicacao) {
        try {
            List<Map<String, Object>> comentarios = comentarioRepository.buscarComentariosPorPublicacao(idPublicacao);
            if (comentarios.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(comentarios);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    /*
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
                ;
                return ResponseEntity.ok().body(publicacaoCurtidaRepository.delete(idPublicacao, idPessoa));
            } else {
                return ResponseEntity.ok(publicacaoCurtidaRepository.save( idPublicacao, idPessoa));
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao curtir/descurtir a publicação. " + e.getMessage());
        }
    }*/
}
