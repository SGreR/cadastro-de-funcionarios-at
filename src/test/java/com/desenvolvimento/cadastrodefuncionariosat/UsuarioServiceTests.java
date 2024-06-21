package com.desenvolvimento.cadastrodefuncionariosat;

import com.desenvolvimento.cadastrodefuncionariosat.model.Usuario;
import com.desenvolvimento.cadastrodefuncionariosat.repository.UsuarioRepository;
import com.desenvolvimento.cadastrodefuncionariosat.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class UsuarioServiceTests {

    @Mock
    private UsuarioRepository usuarioRepository;

    private Usuario usuario;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.usuario = new Usuario();
        this.usuario.setId("1");
        this.usuario.setNome("Fernando Alves");
        this.usuario.setSenha("fernando.alves");
        this.usuario.setPapel("Cliente");
    }

    @Test
    public void testGetById() throws Exception {
        when(usuarioRepository.findById(usuario.getId())).thenReturn(Optional.ofNullable(usuario));

        Usuario encontrado = usuarioService.getUsuarioById(usuario.getId());

        assertEquals(usuario, encontrado);
        verify(usuarioRepository, times(1)).findById(usuario.getId());

    }

    @Test
    public void testCreate() throws Exception {
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario criado = usuarioService.createUsuario(usuario);

        assertEquals(usuario, criado);
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    public void testUpdate() {
        String id = usuario.getId();
        Usuario usuarioAtualizado = new Usuario();
        usuarioAtualizado.setNome("Joana Siqueira");
        usuarioAtualizado.setSenha("joana.siqueira");
        usuarioAtualizado.setPapel("Admin");


        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(usuarioAtualizado)).thenReturn(usuarioAtualizado);

        Usuario result = usuarioService.updateUsuario(id, usuarioAtualizado);

        assertEquals(id, result.getId());
        assertEquals("Joana Siqueira", result.getNome());
        assertEquals("joana.siqueira", result.getSenha());
        assertEquals("Admin", result.getPapel());

        verify(usuarioRepository).findById(id);
        verify(usuarioRepository).save(usuarioAtualizado);
    }

    @Test
    public void testDeleteById() {
        String id = usuario.getId();
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));

        usuarioService.deleteUsuario(id);

        verify(usuarioRepository).findById(id);
        verify(usuarioRepository).deleteById(id);
    }

}
