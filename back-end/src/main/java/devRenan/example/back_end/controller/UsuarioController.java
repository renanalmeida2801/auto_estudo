package devRenan.example.back_end.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import devRenan.example.back_end.model.UsuarioModel;
import devRenan.example.back_end.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RequestMapping("/usuarios")
@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class UsuarioController {
    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping()
    public UsuarioModel saveUser(@RequestBody UsuarioModel user) {
        return usuarioRepository.save(user);
    }

    @GetMapping()
    public List<UsuarioModel> listUsers() {
        return usuarioRepository.findAll();
    }

    @PutMapping("/{id}")
    public UsuarioModel editUser(@PathVariable Long id, @RequestBody UsuarioModel user) {
        return usuarioRepository.findById(id)
        .map(usuario ->{
            usuario.setNome(user.getNome());
            usuario.setEmail(user.getEmail());
            usuario.setCurso(user.getCurso());
            return usuarioRepository.save(usuario);
        }).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
    }
    
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        usuarioRepository.deleteById(id);
    }
}
