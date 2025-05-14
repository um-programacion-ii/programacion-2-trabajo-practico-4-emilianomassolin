package org.example.servicio;

import org.example.modelo.Prestamo;

import java.util.List;

public interface PrestamoService {
    Prestamo guardar(Prestamo prestamo);
    List<Prestamo> obtenerTodos();
    void eliminar(Long id);
    Prestamo buscarPorId(Long id);
}
