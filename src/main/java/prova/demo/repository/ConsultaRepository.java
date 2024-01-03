package prova.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import prova.demo.domain.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long>{
    
}
