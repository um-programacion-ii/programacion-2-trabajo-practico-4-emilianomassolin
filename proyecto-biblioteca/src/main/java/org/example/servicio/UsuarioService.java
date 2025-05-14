package org.example.servicio;

import org.example.modelo.Usuario;

import java.util.List;

public interface UsuarioService {
    Usuario buscarPorEmail(String email);
    List<Usuario> obtenerTodos();
    Usuario guardar(Usuario usuario);
    void eliminar(Long id);
    Usuario actualizar(Long id, Usuario usuario);
}