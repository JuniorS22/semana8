package com.codigo.semana8.service;

import com.codigo.semana8.model.Categoria;
import com.codigo.semana8.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> obtenerTodasLasCategorias() {
        return categoriaRepository.findAll();
    }

    public Categoria obtenerCategoriaPorId(Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if (categoria.isPresent()) {
            return categoria.get();
        } else {
            throw new RuntimeException("Categoría no encontrada con ID: " + id);
        }
    }

    public Categoria crearCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria actualizarCategoria(Long id, Categoria categoriaActualizada) {
        Categoria categoriaExistente = obtenerCategoriaPorId(id);

        categoriaExistente.setNombre(categoriaActualizada.getNombre());
        categoriaExistente.setEstado(categoriaActualizada.getEstado());

        return categoriaRepository.save(categoriaExistente);
    }

    public List<Categoria> obtenerCategoriaPorEstado(int estado){
        return categoriaRepository.findByEstado(estado);
    }

    public boolean eliminarLogico(Long id){
        Categoria categoriaExistente=obtenerCategoriaPorId(id);
        if(categoriaExistente.getEstado()==1){
            categoriaExistente.setEstado(0);
            categoriaRepository.save(categoriaExistente);
            return true;
        }
        return false;
    }

}
