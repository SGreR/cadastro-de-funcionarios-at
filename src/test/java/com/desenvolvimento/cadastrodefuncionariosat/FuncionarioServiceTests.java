package com.desenvolvimento.cadastrodefuncionariosat;

import com.desenvolvimento.cadastrodefuncionariosat.model.Funcionario;
import com.desenvolvimento.cadastrodefuncionariosat.repository.FuncionarioRepository;
import com.desenvolvimento.cadastrodefuncionariosat.service.FuncionarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.fail;

public class FuncionarioServiceTests {

    @Mock
    private FuncionarioRepository funcionarioRepository;

    private Funcionario funcionario;

    @InjectMocks
    private FuncionarioService funcionarioService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        this.funcionario = new Funcionario();
        this.funcionario.setId(1L);
        this.funcionario.setNome("Henrique Muller");
        this.funcionario.setEndereco("Rua 24 de Outubro, 635");
        this.funcionario.setTelefone("46287349");
        this.funcionario.setEmail("henrique.muller@gmail.com");

        try {
            Date date = dateFormat.parse("1983-09-08");
            this.funcionario.setDataNascimento(date);
        } catch (ParseException e) {
            fail("Date parsing failed");
        }
    }

    @Test
    public void testGetFuncionarioById() throws Exception {
        when(funcionarioRepository.findById(funcionario.getId())).thenReturn(Optional.ofNullable(funcionario));

        Funcionario encontrado = funcionarioService.getFuncionarioById(funcionario.getId());

        assertEquals(funcionario, encontrado);
        verify(funcionarioRepository, times(1)).findById(funcionario.getId());

    }

    @Test
    public void testCreate() throws Exception {
        when(funcionarioRepository.save(funcionario)).thenReturn(funcionario);

        Funcionario criado = funcionarioService.createFuncionario(funcionario);

        assertEquals(funcionario, criado);
        verify(funcionarioRepository, times(1)).save(funcionario);
    }

    @Test
    public void testUpdateFuncionario() {
        Long id = funcionario.getId();
        Funcionario funcionarioAtualizado = new Funcionario();
        funcionarioAtualizado.setNome("Henrique Costa");
        funcionarioAtualizado.setEndereco("Rua Nova, 123");
        funcionarioAtualizado.setTelefone("46287350");
        funcionarioAtualizado.setEmail("henrique.costa@gmail.com");

        when(funcionarioRepository.findById(id)).thenReturn(Optional.of(funcionario));
        when(funcionarioRepository.save(funcionarioAtualizado)).thenReturn(funcionarioAtualizado);

        Funcionario result = funcionarioService.updateFuncionario(id, funcionarioAtualizado);

        assertEquals(id, result.getId());
        assertEquals("Henrique Costa", result.getNome());
        assertEquals("Rua Nova, 123", result.getEndereco());
        assertEquals("46287350", result.getTelefone());
        assertEquals("henrique.costa@gmail.com", result.getEmail());

        verify(funcionarioRepository).findById(id);
        verify(funcionarioRepository).save(funcionarioAtualizado);
    }

    @Test
    public void testDeleteFuncionarioById() {
        Long id = funcionario.getId();
        when(funcionarioRepository.findById(id)).thenReturn(Optional.of(funcionario));

        funcionarioService.deleteFuncionarioById(id);

        verify(funcionarioRepository).findById(id);
        verify(funcionarioRepository).deleteById(id);
    }

}
