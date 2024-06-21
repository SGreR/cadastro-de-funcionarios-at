package com.desenvolvimento.cadastrodefuncionariosat.service;

import com.desenvolvimento.cadastrodefuncionariosat.model.Funcionario;
import com.desenvolvimento.cadastrodefuncionariosat.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    @GetMapping
    public List<Funcionario> getAllFuncionarios() {
        return funcionarioRepository.findAll();
    }

    public Funcionario getFuncionarioById(Long id) {
        return funcionarioRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Funcionario createFuncionario(@RequestBody Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    @PutMapping("/{id}")
    public Funcionario updateFuncionario(@PathVariable Long id, @RequestBody Funcionario funcionarioAtualizado) {
        Funcionario funcionarioAntigo = funcionarioRepository.findById(id).orElseThrow();
        funcionarioAtualizado.setId(funcionarioAntigo.getId());
        return funcionarioRepository.save(funcionarioAtualizado);
    }

    @DeleteMapping("/{id}")
    public void deleteFuncionarioById(@PathVariable Long id) {
        funcionarioRepository.findById(id).orElseThrow();
        funcionarioRepository.deleteById(id);
    }
}
