package prodtalk.endpoint;

import entity.Cadastro;
import java.sql.SQLException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prodtalk.repository.CadastroRepository;
import utils.http.Response;

@RestController
@CrossOrigin("*")
public class CadastroEndpoint {
    
    CadastroRepository cadastroRepository = new CadastroRepository();

    @GetMapping("/cadastro")
    public ResponseEntity<Response> getCadastro(@RequestParam("email") String email,
            @RequestParam("login") String login, @RequestParam("senha") String senha) {
        try {
            return Response.ok(cadastroRepository.getCadastro(email, login, senha));
        } catch (SQLException e) {
            return Response.invalid("Erro ao buscar o cadastro. " + e.getMessage());
        }
    }

    @PostMapping("/cadastro")
    public ResponseEntity<Response> addCadastro(@RequestBody Cadastro cadastro) {
        try {
            return cadastroRepository.salvarCadastro(cadastro);
        } catch (SQLException e) {
            return Response.invalid("Erro ao realizar o cadastro. " + e.getMessage());
        }
    }

    @PutMapping("/cadastro")
    public ResponseEntity<Response> updateBook(@RequestBody Cadastro cadastro) {
        try {
            return cadastroRepository.salvarCadastro(cadastro);
        } catch (SQLException e) {
            return Response.invalid("Erro ao realizar o cadastro. " + e.getMessage());
        }
    }

    @DeleteMapping("/cadastro")
    public ResponseEntity<Response> deleteCadastro(@RequestBody Cadastro cadastro) {
        try {
            return cadastroRepository.salvarCadastro(cadastro);
        } catch (SQLException e) {
            return Response.invalid("Erro ao realizar o cadastro. " + e.getMessage());
        }
    }

}
