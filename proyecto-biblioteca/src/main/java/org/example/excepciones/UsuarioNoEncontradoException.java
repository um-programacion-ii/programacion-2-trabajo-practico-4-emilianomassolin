package org.example.excepciones;


public class UsuarioNoEncontradoException extends RuntimeException {
    public UsuarioNoEncontradoException(Long id) {
        super("Usuario no encontrado con ID: " + id);
    }

    public UsuarioNoEncontradoException(String email) {
        super("Usuario no encontrado con email: " + email);
    }
}
