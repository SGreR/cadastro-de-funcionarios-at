package com.desenvolvimento.cadastrodefuncionariosat;

import com.desenvolvimento.cadastrodefuncionariosat.controller.FuncionarioController;
import com.desenvolvimento.cadastrodefuncionariosat.model.Funcionario;
import com.desenvolvimento.cadastrodefuncionariosat.service.FuncionarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.fail;

@WebMvcTest(controllers = FuncionarioController.class)
@AutoConfigureMockMvc(addFilters = false)
public class FuncionarioAPITests {


    private Funcionario funcionario;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FuncionarioService funcionarioService;

    @BeforeEach
    public void setUp() {
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
        when(funcionarioService.getFuncionarioById(funcionario.getId())).thenReturn(funcionario);

        mockMvc.perform(get("/funcionarios/" + funcionario.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(funcionario.getId()))
                .andExpect(jsonPath("$.nome").value(funcionario.getNome()))
                .andExpect(jsonPath("$.endereco").value(funcionario.getEndereco()))
                .andExpect(jsonPath("$.telefone").value(funcionario.getTelefone()))
                .andExpect(jsonPath("$.email").value(funcionario.getEmail()))
                .andExpect(jsonPath("$.dataNascimento").value("1983-09-08T03:00:00.000+00:00"));

        verify(funcionarioService, times(1)).getFuncionarioById(funcionario.getId());

    }

    @Test
    public void testCreate() throws Exception {
        when(funcionarioService.createFuncionario(funcionario)).thenReturn(funcionario);

        mockMvc.perform((post("/funcionarios"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(funcionario)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Criado com sucesso")
                );
        verify(funcionarioService, times(1)).createFuncionario(funcionario);
    }

    @Test
    public void testUpdate() throws Exception {
        when(funcionarioService.updateFuncionario(funcionario.getId(), funcionario)).thenReturn(funcionario);
        mockMvc.perform((put("/funcionarios/" + funcionario.getId()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(funcionario)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Atualizado com sucesso")
                );
        verify(funcionarioService, times(1)).updateFuncionario(funcionario.getId(), funcionario);
    }

    @Test
    public void testDelete() throws Exception {
        doNothing().when(funcionarioService).deleteFuncionarioById(funcionario.getId());
        mockMvc.perform((delete("/funcionarios/" + funcionario.getId())))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$.message").value("Deletado com sucesso")
                );
        verify(funcionarioService, times(1)).deleteFuncionarioById(funcionario.getId());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
