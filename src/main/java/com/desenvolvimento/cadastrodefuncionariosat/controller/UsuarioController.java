package com.desenvolvimento.cadastrodefuncionariosat.controller;

import com.desenvolvimento.cadastrodefuncionariosat.model.Usuario;
import com.desenvolvimento.cadastrodefuncionariosat.payload.MessagePayload;
import com.desenvolvimento.cadastrodefuncionariosat.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> getAll() {
        return ResponseEntity.ok(usuarioService.getAllUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        try{
            Usuario encontrado = usuarioService.getUsuarioById(id);
            return ResponseEntity.ok(encontrado);
        }catch (Exception ex){
            Map<String, String> message = Map.of("Message", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

    @PostMapping
    public ResponseEntity<MessagePayload> create(@RequestBody Usuario usuario) {
        usuarioService.createUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessagePayload("Criado com sucesso"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessagePayload> update(@PathVariable String id, @RequestBody Usuario novoUsuario) {
        try {
            usuarioService.updateUsuario(id, novoUsuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(new MessagePayload("Atualizado com sucesso"));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload(ex.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessagePayload> delete(@PathVariable String id) {
        try {
            usuarioService.deleteUsuario(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new MessagePayload("Deletado com sucesso"));
        }catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload(ex.getMessage()));
        }
    }
}
