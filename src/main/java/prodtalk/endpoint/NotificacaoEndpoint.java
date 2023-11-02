package prodtalk.endpoint;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prodtalk.entity.Notificacao;
import prodtalk.repository.NotificacaoRepository;

@RestController
@CrossOrigin("*")
public class NotificacaoEndpoint {

    private final NotificacaoRepository notificacaoRepository;

    public NotificacaoEndpoint() {
        this.notificacaoRepository = new NotificacaoRepository();
    }

    @GetMapping("/notificacao")
    public ResponseEntity<List<Notificacao>> getNotificacoes(@RequestParam("idPessoa") Long idPessoa) throws Exception {
        try {
            List<Notificacao> notificacoes = notificacaoRepository.buscarNotificacoesPorIdPessoa(idPessoa);
            return ResponseEntity.ok(notificacoes);
        } catch (SQLException e) {
            List<Notificacao> emptyList = new ArrayList<>(); // Crie uma lista vazia ou trate de outra forma
            return ResponseEntity.badRequest().body(emptyList);
        }
    }
}
