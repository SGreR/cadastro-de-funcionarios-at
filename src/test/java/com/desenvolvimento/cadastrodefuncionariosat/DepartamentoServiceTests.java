package com.desenvolvimento.cadastrodefuncionariosat;

import com.desenvolvimento.cadastrodefuncionariosat.model.Departamento;
import com.desenvolvimento.cadastrodefuncionariosat.repository.DepartamentoRepository;
import com.desenvolvimento.cadastrodefuncionariosat.service.DepartamentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DepartamentoServiceTests {

    @Mock
    private DepartamentoRepository departamentoRepository;

    private Departamento departamento;

    @InjectMocks
    private DepartamentoService departamentoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        this.departamento = new Departamento();
        this.departamento.setId(1L);
        this.departamento.setNome("TI");
        this.departamento.setLocal("Prédio A");
        this.departamento.setFuncionarios(new ArrayList<>());
    }

    @Test
    public void testGetById() throws Exception {
        when(departamentoRepository.findById(departamento.getId())).thenReturn(Optional.ofNullable(departamento));

        Departamento encontrado = departamentoService.getDepartamentoById(departamento.getId());

        assertEquals(departamento, encontrado);
        verify(departamentoRepository, times(1)).findById(departamento.getId());

    }

    @Test
    public void testCreate() throws Exception {
        when(departamentoRepository.save(departamento)).thenReturn(departamento);

        Departamento criado = departamentoService.createDepartamento(departamento);

        assertEquals(departamento, criado);
        verify(departamentoRepository, times(1)).save(departamento);
    }

    @Test
    public void testUpdate() {
        Long id = departamento.getId();
        Departamento departamentoAtualizado = new Departamento();
        departamentoAtualizado.setNome("Vendas");
        departamentoAtualizado.setLocal("Prédio D");

        when(departamentoRepository.findById(id)).thenReturn(Optional.of(departamento));
        when(departamentoRepository.save(departamentoAtualizado)).thenReturn(departamentoAtualizado);

        Departamento result = departamentoService.updateDepartamento(id, departamentoAtualizado);

        assertEquals(id, result.getId());
        assertEquals("Vendas", result.getNome());
        assertEquals("Prédio D", result.getLocal());

        verify(departamentoRepository).findById(id);
        verify(departamentoRepository).save(departamentoAtualizado);
    }

    @Test
    public void testDeleteById() {
        Long id = departamento.getId();
        when(departamentoRepository.findById(id)).thenReturn(Optional.of(departamento));

        departamentoService.deleteDepartamentoById(id);

        verify(departamentoRepository).findById(id);
        verify(departamentoRepository).deleteById(id);
    }

}
