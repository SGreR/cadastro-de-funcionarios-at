package com.desenvolvimento.cadastrodefuncionariosat;

import com.desenvolvimento.cadastrodefuncionariosat.controller.UsuarioController;
import com.desenvolvimento.cadastrodefuncionariosat.model.Usuario;
import com.desenvolvimento.cadastrodefuncionariosat.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UsuarioController.class)
@AutoConfigureMockMvc(addFilters = false)
public class UsuarioAPITests {


    private Usuario usuario;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @BeforeEach
    public void setUp() {
        this.usuario = new Usuario();
        this.usuario.setId("1");
        this.usuario.setNome("Fernando Alves");
        this.usuario.setSenha("fernando.alves");
        this.usuario.setPapel("Cliente");
    }

    @Test
    public void testGetFuncionarioById() throws Exception {
        when(usuarioService.getUsuarioById(usuario.getId())).thenReturn(usuario);

        mockMvc.perform(get("/usuarios/" + usuario.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(usuario.getId()))
                .andExpect(jsonPath("$.nome").value(usuario.getNome()))
                .andExpect(jsonPath("$.senha").value(usuario.getSenha()))
                .andExpect(jsonPath("$.papel").value(usuario.getPapel())
                );

        verify(usuarioService, times(1)).getUsuarioById(usuario.getId());

    }

    @Test
    public void testCreate() throws Exception {
        when(usuarioService.createUsuario(usuario)).thenReturn(usuario);

        mockMvc.perform((post("/usuarios"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(usuario)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Criado com sucesso")
                );
        verify(usuarioService, times(1)).createUsuario(usuario);
    }

    @Test
    public void testUpdate() throws Exception {
        when(usuarioService.updateUsuario(usuario.getId(), usuario)).thenReturn(usuario);
        mockMvc.perform((put("/usuarios/" + usuario.getId()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(usuario)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Atualizado com sucesso")
                );
        verify(usuarioService, times(1)).updateUsuario(usuario.getId(), usuario);
    }

    @Test
    public void testDelete() throws Exception {
        doNothing().when(usuarioService).deleteUsuario(usuario.getId());
        mockMvc.perform((delete("/usuarios/" + usuario.getId())))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$.message").value("Deletado com sucesso")
                );
        verify(usuarioService, times(1)).deleteUsuario(usuario.getId());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
