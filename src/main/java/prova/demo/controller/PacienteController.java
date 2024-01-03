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

import prova.demo.domain.Paciente;
import prova.demo.repository.PacienteRepository;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<Object> adicionar(@RequestBody Paciente paciente,
            UriComponentsBuilder uriBuilder) {
        Paciente pacienteLocal = repository.save(paciente);
        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(pacienteLocal.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscar(@PathVariable Long id) {
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
    public ResponseEntity<Paciente> atualizar(@RequestBody Paciente paciente) {
        Paciente pacienteLocal = repository.findById(paciente.getId()).get();
        pacienteLocal.setNome(paciente.getNome());
        return ResponseEntity.ok(pacienteLocal);
    }
}

