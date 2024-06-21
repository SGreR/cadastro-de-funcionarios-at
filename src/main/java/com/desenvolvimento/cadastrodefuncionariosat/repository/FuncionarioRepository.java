package com.desenvolvimento.cadastrodefuncionariosat.repository;

import com.desenvolvimento.cadastrodefuncionariosat.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}
