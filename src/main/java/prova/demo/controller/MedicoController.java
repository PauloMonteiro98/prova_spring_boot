package prova.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import jakarta.transaction.Transactional;

import prova.demo.domain.Medico;
import prova.demo.repository.MedicoRepository;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<Object> adicionar(@RequestBody Medico medico,
            UriComponentsBuilder uriBuilder) {
        Medico medicoLocal = repository.save(medico);
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medicoLocal.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(repository.getReferenceById(id));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> remover(@PathVariable Long id) {
        repository.delete(repository.getReferenceById(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Medico> atualizar(@RequestBody Medico medico) {
        Medico medicoLocal = repository.findById(medico.getId()).get();
        medicoLocal.setNome(medico.getNome());
        return ResponseEntity.ok(medicoLocal);
    }
}
