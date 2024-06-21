package com.desenvolvimento.cadastrodefuncionariosat.service;

import com.desenvolvimento.cadastrodefuncionariosat.model.Departamento;
import com.desenvolvimento.cadastrodefuncionariosat.repository.DepartamentoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class DepartamentoService {

    private final DepartamentoRepository departamentoRepository;

    public DepartamentoService(DepartamentoRepository departamentoRepository){
        this.departamentoRepository = departamentoRepository;
    }

    public List<Departamento> getAllDepartamentos() {
        return departamentoRepository.findAll();
    }

    public Departamento getDepartamentoById(Long id) {
        return departamentoRepository.findById(id).orElse(null);
    }

    public Departamento createDepartamento(@RequestBody Departamento departamento) {
        return departamentoRepository.save(departamento);
    }

    public Departamento updateDepartamento(@PathVariable Long id, @RequestBody Departamento departamentoAtualizado) {
        Departamento departamentoAntigo = departamentoRepository.findById(id).orElseThrow();
        departamentoAtualizado.setId(departamentoAntigo.getId());
        return departamentoRepository.save(departamentoAtualizado);
    }

    public void deleteDepartamentoById(@PathVariable Long id) {
        departamentoRepository.findById(id).orElseThrow();
        departamentoRepository.deleteById(id);
    }
}
