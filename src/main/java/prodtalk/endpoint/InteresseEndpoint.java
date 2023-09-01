package prodtalk.endpoint;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import prodtalk.entity.Interesse;
import prodtalk.repository.InteresseRepository;

@RestController
@RequestMapping("/interesse")
@CrossOrigin("*")
public class InteresseEndpoint {
    private final InteresseRepository interesseRepository;

    @Autowired
    public InteresseEndpoint(InteresseRepository interesseRepository) {
        this.interesseRepository = interesseRepository;
    }

    @GetMapping
    public List<Interesse> buscarPublicacoes(@RequestParam(defaultValue = "1") int page) {
        int pageSize = 10; // Número de publicações por página
        int offset = (page - 1) * pageSize;
        List<Interesse> interesses = interesseRepository.buscarInteresses(offset, pageSize);
        return interesses;
    }

}