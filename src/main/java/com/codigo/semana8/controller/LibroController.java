package com.codigo.semana8.controller;

import com.codigo.semana8.model.Libro;
import com.codigo.semana8.service.LibroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libros")
public class LibroController {

    private final LibroService libroService;


    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping
    public List<Libro> listarLibros() {
       // List<Libro> libros = libroService.obtenerTodosLosLibros();
        List<Libro> libros = libroService.obtenerLibroPorEstado(1);
        return libros;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtenerLibroPorId(@PathVariable Long id) {
        Libro libro = libroService.obtenerLibroPorId(id);
        return ResponseEntity.ok(libro);
    }

    @PostMapping
    public ResponseEntity<Libro> crearLibro(@RequestBody Libro libro) {
        Libro nuevoLibro = libroService.crearLibro(libro);
        return new ResponseEntity<>(nuevoLibro, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizarLibro(@PathVariable Long id, @RequestBody Libro libroActualizado) {
        Libro libroAc = libroService.actualizarLibro(id, libroActualizado);
        return ResponseEntity.ok(libroActualizado);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Boolean> eliminarLogico(@PathVariable Long id){
        if (libroService.eliminarLogico(id)){
            return new ResponseEntity<>(true,HttpStatus.OK);
        }
        return new ResponseEntity<>(false   ,HttpStatus.NOT_FOUND);
    }
}
