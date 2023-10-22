package prodtalk.endpoint;
import prodtalk.entity.Categoria;
import prodtalk.repository.CategoriaRepository;
import utils.http.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/categoria")
@CrossOrigin("*")
public class CategoriaEndpoint {

    private final CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaEndpoint(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @GetMapping
    public ResponseEntity<Response> buscarCategoria(@RequestParam("idCategoria") Long id) throws Exception {
        try {
            Categoria categoria = categoriaRepository.buscarCategoria(id);
            if (categoria != null) {
                return Response.ok(categoria);
            } else {
                return Response.invalid("Categoria não encontrada.");
            }
        } catch (SQLException e) {
            return Response.invalid("Erro ao buscar a categoria. " + e.getMessage());
        }
    }
    
    @GetMapping("/categorias")
    public ResponseEntity<Response> buscarTodasCategorias() throws Exception {
        try {
            List<Categoria> categorias = categoriaRepository.buscarTodasCategorias();
            return Response.ok(categorias);
        } catch (SQLException e) {
            return Response.invalid("Erro ao buscar todas as categorias. " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Response> addCategoria(@RequestBody Categoria categoria) throws Exception {
        try {
            return categoriaRepository.salvarCategoria(categoria);
        } catch (SQLException e) {
            return Response.invalid("Erro ao realizar o cadastro da categoria. " + e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<Response> alterarCategoria(@RequestBody Categoria categoria) throws Exception {
        try {
            return categoriaRepository.alterarCategoria(categoria);
        } catch (SQLException e) {
            return Response.invalid("Erro ao atualizar a categoria. " + e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<Response> deletarCategoria(@RequestParam("id") Long id) throws Exception {
        try {
            return categoriaRepository.deletarCategoria(id);
        } catch (SQLException e) {
            return Response.invalid("Erro ao deletar a categoria. " + e.getMessage());
        }
    }
}
