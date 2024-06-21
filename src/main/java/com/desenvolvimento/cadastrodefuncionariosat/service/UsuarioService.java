package com.desenvolvimento.cadastrodefuncionariosat.service;

import com.desenvolvimento.cadastrodefuncionariosat.model.Usuario;
import com.desenvolvimento.cadastrodefuncionariosat.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public Usuario getUsuarioById(@PathVariable String id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Usuario createUsuario(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @PutMapping("/{id}")
    public Usuario updateUsuario(@PathVariable String id, @RequestBody Usuario novoUsuario) {
        Usuario usuarioAntigo = usuarioRepository.findById(id).orElseThrow();
        novoUsuario.setId(usuarioAntigo.getId());
        return usuarioRepository.save(novoUsuario);
    }

    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable String id) {
        usuarioRepository.findById(id).orElseThrow();
        usuarioRepository.deleteById(id);
    }
}
