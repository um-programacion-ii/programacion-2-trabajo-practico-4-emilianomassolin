import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.example.repositorio.impl.LibroRepositoryImpl;
import org.example.modelo.Libro;
import org.example.modelo.EstadoLibro;

class LibroRepositoryImplTest {
    private final LibroRepositoryImpl libroRepository = new LibroRepositoryImpl();

    @Test
    void guardarYRecuperarLibro() {
        Libro libro = new Libro(null, "111-222", "Test", "Autor", EstadoLibro.DISPONIBLE);
        Libro guardado = libroRepository.save(libro);

        assertNotNull(guardado.getId());
        assertEquals("111-222", libroRepository.findById(guardado.getId()).get().getIsbn());
    }
}