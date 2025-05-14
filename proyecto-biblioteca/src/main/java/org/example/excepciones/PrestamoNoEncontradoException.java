package org.example.excepciones;

public class PrestamoNoEncontradoException extends RuntimeException {
    public PrestamoNoEncontradoException(Long id) {
        super("Préstamo no encontrado con ID: " + id);
    }
}
