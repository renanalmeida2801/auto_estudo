package devRenan.example.back_end.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import devRenan.example.back_end.model.UsuarioModel;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long>{
    
}
