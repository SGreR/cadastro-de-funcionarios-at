package com.desenvolvimento.cadastrodefuncionariosat.repository;

import com.desenvolvimento.cadastrodefuncionariosat.model.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
}
