package org.example.servicio.impl;

import org.example.modelo.EstadoLibro;
import org.example.modelo.Libro;
import org.example.servicio.LibroService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LibroServiceImpl implements LibroService {

    private final List<Libro> libros = new ArrayList<>();
    private Long nextId = 1L;
    public LibroServiceImpl() {
        // Inicializar datos de prueba
        libros.add(new Libro(nextId++, "123456789", "Libro A", "Autor A", EstadoLibro.DISPONIBLE));
        libros.add(new Libro(nextId++, "987654321", "Libro B", "Autor B", EstadoLibro.PRESTADO));
    }
    @Override
    public Libro buscarPorIsbn(String isbn) {
        return libros.stream()
                .filter(libro -> libro.getIsbn().equals(isbn))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Libro no encontrado con ISBN: " + isbn));
    }

    @Override
    public List<Libro> obtenerTodos() {
        return new ArrayList<>(libros);
    }

    @Override
    public Libro guardar(Libro libro) {
        libro.setId(nextId++);
        libros.add(libro);
        return libro;
    }

    @Override
    public void eliminar(Long id) {
        libros.removeIf(libro -> libro.getId().equals(id));
    }

    @Override
    public Libro actualizar(Long id, Libro libro) {
        Libro existente = buscarPorId(id);
        existente.setIsbn(libro.getIsbn());
        existente.setTitulo(libro.getTitulo());
        existente.setAutor(libro.getAutor());
        existente.setEstado(libro.getEstado());
        return existente;
    }

    @Override
    public Libro buscarPorId(Long id) {
        return libros.stream()
                .filter(libro -> libro.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Libro no encontrado con ID: " + id));
    }
}