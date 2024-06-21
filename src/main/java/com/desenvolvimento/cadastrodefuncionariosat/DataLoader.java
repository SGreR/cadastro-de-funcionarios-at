package com.desenvolvimento.cadastrodefuncionariosat;

import com.desenvolvimento.cadastrodefuncionariosat.model.Funcionario;
import com.desenvolvimento.cadastrodefuncionariosat.model.Departamento;
import com.desenvolvimento.cadastrodefuncionariosat.model.Usuario;
import com.desenvolvimento.cadastrodefuncionariosat.repository.DepartamentoRepository;
import com.desenvolvimento.cadastrodefuncionariosat.repository.FuncionarioRepository;
import com.desenvolvimento.cadastrodefuncionariosat.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Departamento departamento1 = new Departamento();
        departamento1.setNome("TI");
        departamento1.setLocal("Prédio A");

        Departamento departamento2 = new Departamento();
        departamento2.setNome("RH");
        departamento2.setLocal("Prédio B");

        departamentoRepository.saveAll(Arrays.asList(departamento1, departamento2));

        Funcionario funcionario1 = new Funcionario();
        funcionario1.setNome("Pedro Silva");
        funcionario1.setEndereco("Rua das Laranjeiras, 729");
        funcionario1.setTelefone("78468573");
        funcionario1.setEmail("pedro.silva@gmail.com");
        funcionario1.setDataNascimento(dateFormat.parse("1985-10-15"));
        funcionario1.setDepartamento(departamento1);

        Funcionario funcionario2 = new Funcionario();
        funcionario2.setNome("Anna Costa");
        funcionario2.setEndereco("Rua 7 de Setembro, 598");
        funcionario2.setTelefone("46821975");
        funcionario2.setEmail("anna.costa@gmail.com");
        funcionario2.setDataNascimento(dateFormat.parse("1990-05-25"));
        funcionario2.setDepartamento(departamento1);

        Funcionario funcionario3 = new Funcionario();
        funcionario3.setNome("Carlos Mendez");
        funcionario3.setEndereco("Rua da Praia, 671");
        funcionario3.setTelefone("49765197");
        funcionario3.setEmail("carlos.mendez@gmail.com");
        funcionario3.setDataNascimento(dateFormat.parse("1982-08-30"));
        funcionario3.setDepartamento(departamento2);

        funcionarioRepository.saveAll(Arrays.asList(funcionario1, funcionario2, funcionario3));

        Usuario usuario1 = new Usuario();
        usuario1.setId("1");
        usuario1.setNome("Fernando Alves");
        usuario1.setSenha("fernando.alves");
        usuario1.setPapel("Cliente");

        Usuario usuario2 = new Usuario();
        usuario2.setId("2");
        usuario2.setNome("Anna Costa");
        usuario2.setSenha("anna.costa");
        usuario2.setPapel("Admin");

        usuarioRepository.save(usuario1);
        usuarioRepository.save(usuario2);
    }
}

