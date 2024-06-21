package com.desenvolvimento.cadastrodefuncionariosat.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String local;

    @OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Funcionario> funcionarios;
}
