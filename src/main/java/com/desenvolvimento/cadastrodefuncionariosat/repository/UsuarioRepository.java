package com.desenvolvimento.cadastrodefuncionariosat.repository;

import com.desenvolvimento.cadastrodefuncionariosat.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
}