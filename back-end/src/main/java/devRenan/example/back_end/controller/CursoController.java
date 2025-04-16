package devRenan.example.back_end.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import devRenan.example.back_end.model.CursoModel;
import devRenan.example.back_end.repository.CursoRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/cursos")
@CrossOrigin(origins = "http://localhost:5173")
public class CursoController {
    private CursoRepository cursoRepository;

    public CursoController(CursoRepository cursoRepository){
        this.cursoRepository = cursoRepository;
    }

    @PostMapping()
    public CursoModel saveCurso(@RequestBody CursoModel curso) {
        return cursoRepository.save(curso);
    }

    @GetMapping()
    public List<CursoModel> listCursos() {
        return cursoRepository.findAll();
    }

    @PutMapping("/{id}")
    public CursoModel updateCurso(@PathVariable Long id, @RequestBody CursoModel course) {
        return cursoRepository.findById(id)
        .map(curso ->{
            curso.setNome(course.getNome());
            curso.setSigla(course.getSigla());
            curso.setTurno(course.getTurno());
            return cursoRepository.save(curso);
        }).orElseThrow(()-> new RuntimeException("Curso nao encontrado"));
        
    }

    @DeleteMapping("/{id}")
    public void deleteCurso(@PathVariable Long id){
        cursoRepository.deleteById(id);
    }
    
    
}
