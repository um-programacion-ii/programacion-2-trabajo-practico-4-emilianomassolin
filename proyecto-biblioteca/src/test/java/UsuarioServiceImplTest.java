import org.example.excepciones.UsuarioNoEncontradoException;
import org.example.modelo.EstadoUsuario;
import org.example.modelo.Usuario;
import org.example.repositorio.UsuarioRepository;
import org.example.servicio.impl.UsuarioServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @Test
    void cuandoBuscarPorEmailExiste_entoncesRetornaUsuario() {
        String email = "ejemplo@mail.com";
        Usuario usuario = new Usuario(1L, "Juan", email, EstadoUsuario.ACTIVO);
        when(usuarioRepository.findByEmail(email)).thenReturn(Optional.of(usuario));

        Usuario resultado = usuarioService.buscarPorEmail(email);

        assertNotNull(resultado);
        assertEquals(email, resultado.getEmail());
        verify(usuarioRepository).findByEmail(email);
    }

    @Test
    void cuandoBuscarPorEmailNoExiste_entoncesLanzaExcepcion() {
        String email = "inexistente@mail.com";
        when(usuarioRepository.findByEmail(email)).thenReturn(Optional.empty());

        assertThrows(UsuarioNoEncontradoException.class, () -> usuarioService.buscarPorEmail(email));
    }
}