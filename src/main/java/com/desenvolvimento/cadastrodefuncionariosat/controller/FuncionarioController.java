package com.desenvolvimento.cadastrodefuncionariosat.controller;

import com.desenvolvimento.cadastrodefuncionariosat.model.Funcionario;
import com.desenvolvimento.cadastrodefuncionariosat.payload.MessagePayload;
import com.desenvolvimento.cadastrodefuncionariosat.service.FuncionarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @GetMapping
    public ResponseEntity<List<Funcionario>> getAll() {
        return ResponseEntity.ok(funcionarioService.getAllFuncionarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try{
            Funcionario encontrado = funcionarioService.getFuncionarioById(id);
            return ResponseEntity.ok(encontrado);
        }catch (Exception e){
            Map<String, String> message = Map.of("Message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

    @PostMapping
    public ResponseEntity<MessagePayload> create(@RequestBody Funcionario funcionario) {
        funcionarioService.createFuncionario(funcionario);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessagePayload("Criado com sucesso"));    }

    @PutMapping("/{id}")
    public ResponseEntity<MessagePayload> update(@PathVariable Long id, @RequestBody Funcionario funcionarioAtualizado) {
        try {
            funcionarioService.updateFuncionario(id,funcionarioAtualizado);
            return ResponseEntity.status(HttpStatus.CREATED).body(new MessagePayload("Atualizado com sucesso"));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload(ex.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessagePayload> delete(@PathVariable Long id) {
        try {
            funcionarioService.deleteFuncionarioById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new MessagePayload("Deletado com sucesso"));
        }catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload(ex.getMessage()));
        }
    }
}
