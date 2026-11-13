package com.example.libros;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final List<Libro> libros = new CopyOnWriteArrayList<>();
    private final AtomicLong contador = new AtomicLong(1);

    public LibroController() {
        libros.add(new Libro(contador.getAndIncrement(), "Cien años de soledad", "Gabriel García Márquez", 1967, 45.90));
    }

    @GetMapping
    public List<Libro> listar() {
        return libros;
    }

    @PostMapping 
    public Libro crear(@RequestBody Libro nuevo) {
        Libro guardado = new Libro(contador.getAndIncrement(), nuevo.titulo(), nuevo.autor(), nuevo.anio(), nuevo.precio());
        libros.add(guardado);
        return guardado;
    }
}