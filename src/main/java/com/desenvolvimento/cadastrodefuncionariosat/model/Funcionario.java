package com.desenvolvimento.cadastrodefuncionariosat.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Data
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String endereco;
    private String telefone;
    private String email;
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    @ManyToOne
    @JoinColumn(name = "departamento_id")
    @JsonBackReference
    private Departamento departamento;
}
