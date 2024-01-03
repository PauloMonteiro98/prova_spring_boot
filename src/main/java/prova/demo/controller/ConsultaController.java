package prova.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import jakarta.transaction.Transactional;

import prova.demo.domain.Consulta;
import prova.demo.repository.ConsultaRepository;

@RestController
@RequestMapping("consultas")
public class ConsultaController {
    @Autowired
    private ConsultaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<Object> criarConsulta(@RequestBody Consulta consulta,
            UriComponentsBuilder uriBuilder) {
        Consulta consultaLocal = repository.save(consulta);
        var uri = uriBuilder.path("/consultas/{id}").buildAndExpand(consultaLocal.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consulta> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(repository.getReferenceById(id));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Consulta> atualizarConsulta(@RequestBody Consulta consulta) {
        Consulta consultaLocal = repository.findById(consulta.getId()).get();
        consultaLocal.setId(consulta.getId());
        return ResponseEntity.ok(consultaLocal);
    }
}