package prodtalk.endpoint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.sql.SQLException;
import org.springframework.http.ResponseEntity;
import prodtalk.entity.Pessoa;
import org.springframework.web.bind.annotation.*;
import prodtalk.repository.PessoaRepository;
import utils.http.Response;

@RestController
@CrossOrigin("*")
@JsonIgnoreProperties({"*", "handler"})

public class PessoaEndpoint {

    PessoaRepository pessoaRepository = new PessoaRepository();

    @GetMapping("/pessoa")
    public ResponseEntity<Response> getPessoa(@RequestParam("idCadastro") long idCadastro) {
        try {
            return Response.ok(pessoaRepository.getPessoa(idCadastro));
        } catch (SQLException e) {
            return Response.invalid("Erro ao buscar a pessoa. " + e.getMessage());
        }
    }

    @PostMapping("/pessoa")
    public ResponseEntity<Response> cadastrarPessoa(@RequestBody Pessoa pessoa) {
        return null;
    }

    
    @PutMapping("/pessoa")
    public ResponseEntity<Response>alterarPessoa(@RequestBody Pessoa pessoa) {
        try {
            return Response.ok(pessoaRepository.alterarPessoa(pessoa));
        } catch (SQLException e) {
            return Response.invalid("Erro ao buscar a pessoa. " + e.getMessage());
        }
    }
}
