package prova.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import prova.demo.domain.Paciente;
import prova.demo.repository.PacienteRepository;

@Service
public class Pacientecpf {
    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente cadastrarPaciente(Paciente paciente) {
        if (pacienteRepository.existsByCpf(paciente.getCpf())) {
            throw new RuntimeException("CPF já cadastrado");
        }
        return pacienteRepository.save(paciente);
    }
}

