package prova.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import prova.demo.domain.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long>{

}
