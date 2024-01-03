package prova.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import prova.demo.domain.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long>{

    boolean existsByCpf(String cpf);

}
