import org.example.excepciones.PrestamoNoEncontradoException;
import org.example.modelo.Prestamo;
import org.example.repositorio.PrestamoRepository;
import org.example.servicio.impl.PrestamoServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PrestamoServiceImplTest {

    @Mock
    private PrestamoRepository prestamoRepository;

    @InjectMocks
    private PrestamoServiceImpl prestamoService;

    @Test
    void cuandoBuscarPorIdExiste_entoncesRetornaPrestamo() {
        Long id = 1L;
        Prestamo prestamo = new Prestamo();
        prestamo.setId(id);
        when(prestamoRepository.findById(id)).thenReturn(Optional.of(prestamo));

        Prestamo resultado = prestamoService.buscarPorId(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
    }

    @Test
    void cuandoBuscarPorIdNoExiste_entoncesLanzaExcepcion() {
        Long id = 999L;
        when(prestamoRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(PrestamoNoEncontradoException.class, () -> prestamoService.buscarPorId(id));
    }
}