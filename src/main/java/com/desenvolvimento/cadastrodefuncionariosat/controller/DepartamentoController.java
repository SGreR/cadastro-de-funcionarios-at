package com.desenvolvimento.cadastrodefuncionariosat.controller;

import com.desenvolvimento.cadastrodefuncionariosat.model.Departamento;
import com.desenvolvimento.cadastrodefuncionariosat.payload.MessagePayload;
import com.desenvolvimento.cadastrodefuncionariosat.service.DepartamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {

    private final DepartamentoService departamentoService;

    public DepartamentoController(DepartamentoService departamentoService){
        this.departamentoService = departamentoService;
    }

    @GetMapping
    public ResponseEntity<List<Departamento>> getAll() {
        return ResponseEntity.ok(departamentoService.getAllDepartamentos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try{
            Departamento encontrado = departamentoService.getDepartamentoById(id);
            return ResponseEntity.ok(encontrado);
        }catch (Exception e){
            Map<String, String> message = Map.of("Message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

    @PostMapping
    public ResponseEntity<MessagePayload> create(@RequestBody Departamento departamento) {
        departamentoService.createDepartamento(departamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessagePayload("Criado com sucesso"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessagePayload> update(@PathVariable Long id, @RequestBody Departamento departamentoAtualizado) {
        try {
            departamentoService.updateDepartamento(id, departamentoAtualizado);
            return ResponseEntity.status(HttpStatus.CREATED).body(new MessagePayload("Atualizado com sucesso"));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload(ex.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessagePayload> delete(@PathVariable Long id) {
        try {
            departamentoService.deleteDepartamentoById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new MessagePayload("Deletado com sucesso"));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload(ex.getMessage()));
        }
    }
}
