package com.codigo.semana8.service;

import com.codigo.semana8.model.Libro;
import com.codigo.semana8.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

    private final LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public List<Libro> obtenerTodosLosLibros() {
        return libroRepository.findAll();
    }

    public Libro obtenerLibroPorId(Long id) {
        Optional<Libro> libro = libroRepository.findById(id);
        if (libro.isPresent()) {
            return libro.get();
        } else {
            throw new RuntimeException("Libro no encontrado con ID: " + id);
        }
    }

    public Libro crearLibro(Libro libro) {
        // Puedes realizar validaciones o lógica de negocio aquí antes de guardar el libro
        return libroRepository.save(libro);
    }

    public Libro actualizarLibro(Long id, Libro libroActualizado) {
        Libro libroExistente = obtenerLibroPorId(id);

        // Actualizar los campos necesarios del libro existente con los datos del libro actualizado
        libroExistente.setTitulo(libroActualizado.getTitulo());
        libroExistente.setEstado(libroActualizado.getEstado());
        // Actualiza otros campos aquí

        return libroRepository.save(libroExistente);
    }

    public List<Libro> obtenerLibroPorEstado(int estado){
        return libroRepository.findByEstado(estado);
    }

    public boolean eliminarLogico(Long id){
        Libro libroExistente=obtenerLibroPorId(id);
        if(libroExistente.getEstado()==1){
            libroExistente.setEstado(0);
            libroRepository.save(libroExistente);
            return true;
        }
        return false;
    }
}
