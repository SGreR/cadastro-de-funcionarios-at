package com.desenvolvimento.cadastrodefuncionariosat.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Usuario {

    @Id
    private String id;
    private String nome;
    private String senha;
    private String papel;
}
