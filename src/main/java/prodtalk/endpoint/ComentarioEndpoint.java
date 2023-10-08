package prodtalk.endpoint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import prodtalk.entity.Comentario;
import prodtalk.repository.ComentarioRepository;

@RestController
@RequestMapping("/comentario")
@CrossOrigin("*")
@JsonIgnoreProperties({"*", "handler"})
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

    @PostMapping
    public ResponseEntity<List<Map<String, Object>>> publicarPublicacao(@RequestBody Comentario comentario) {
        try {
            List<Map<String, Object>> comentariosAtualizados = comentarioRepository.salvarComentario(comentario);
            if (comentariosAtualizados.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(comentariosAtualizados);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

}
