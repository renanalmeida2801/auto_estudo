package devRenan.example.back_end.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import devRenan.example.back_end.model.CursoModel;

public interface CursoRepository extends JpaRepository<CursoModel, Long> {
    
}
