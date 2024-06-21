package com.desenvolvimento.cadastrodefuncionariosat;

import com.desenvolvimento.cadastrodefuncionariosat.controller.DepartamentoController;
import com.desenvolvimento.cadastrodefuncionariosat.model.Departamento;
import com.desenvolvimento.cadastrodefuncionariosat.service.DepartamentoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = DepartamentoController.class)
@AutoConfigureMockMvc(addFilters = false)
public class DepartamentoAPITests {


    private Departamento departamento;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartamentoService departamentoService;

    @BeforeEach
    public void setUp() {
        this.departamento = new Departamento();
        this.departamento.setId(1L);
        this.departamento.setNome("TI");
        this.departamento.setLocal("Prédio A");
        this.departamento.setFuncionarios(new ArrayList<>());
    }

    @Test
    public void testGetById() throws Exception {
        when(departamentoService.getDepartamentoById(departamento.getId())).thenReturn(departamento);

        mockMvc.perform(get("/departamentos/" + departamento.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(departamento.getId()))
                .andExpect(jsonPath("$.nome").value(departamento.getNome()))
                .andExpect(jsonPath("$.local").value(departamento.getLocal()))
                .andExpect(jsonPath("$.funcionarios").value(departamento.getFuncionarios()));

        verify(departamentoService, times(1)).getDepartamentoById(departamento.getId());

    }

    @Test
    public void testCreate() throws Exception {
        when(departamentoService.createDepartamento(departamento)).thenReturn(departamento);

        mockMvc.perform((post("/departamentos"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(departamento)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Criado com sucesso")
                );
        verify(departamentoService, times(1)).createDepartamento(departamento);
    }

    @Test
    public void testUpdate() throws Exception {
        when(departamentoService.updateDepartamento(departamento.getId(), departamento)).thenReturn(departamento);
        mockMvc.perform((put("/departamentos/" + departamento.getId()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(departamento)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Atualizado com sucesso")
                );
        verify(departamentoService, times(1)).updateDepartamento(departamento.getId(), departamento);
    }

    @Test
    public void testDelete() throws Exception {
        doNothing().when(departamentoService).deleteDepartamentoById(departamento.getId());
        mockMvc.perform((delete("/departamentos/" + departamento.getId())))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$.message").value("Deletado com sucesso")
                );
        verify(departamentoService, times(1)).deleteDepartamentoById(departamento.getId());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
