package com.codigo.semana8.controller;

import com.codigo.semana8.model.Categoria;
import com.codigo.semana8.service.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;


    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public List<Categoria> listarCategorias() {
        //List<Categoria> categorias = categoriaService.obtenerTodasLasCategorias();
        List<Categoria> categorias = categoriaService.obtenerCategoriaPorEstado(1);
        return categorias;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> obtenerCategoriaPorId(@PathVariable Long id) {
        Categoria categoria = categoriaService.obtenerCategoriaPorId(id);
        return ResponseEntity.ok(categoria);
    }

    @PostMapping
    public ResponseEntity<Categoria> crearCategoria(@RequestBody Categoria categoria) {
        Categoria nuevaCategoria = categoriaService.crearCategoria(categoria);
        return new ResponseEntity<>(nuevaCategoria, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> actualizarCategoria(@PathVariable Long id, @RequestBody Categoria categoriaActualizada) {
        Categoria categoriaAc = categoriaService.actualizarCategoria(id, categoriaActualizada);
        return ResponseEntity.ok(categoriaAc);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Boolean> eliminarLogico(@PathVariable Long id){
        if(categoriaService.eliminarLogico(id)){
            return new ResponseEntity<>(true,HttpStatus.OK);
        }
        return new ResponseEntity<>(false,HttpStatus.NOT_FOUND);
    }
}
