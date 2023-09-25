package com.codigo.semana8.controller;

import com.codigo.semana8.model.Editor;
import com.codigo.semana8.service.EditorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/editores")
public class EditorController {

    private final EditorService editorService;


    public EditorController(EditorService editorService) {
        this.editorService = editorService;
    }

    @GetMapping
    public List<Editor> listarEditores() {
        //List<Editor> editores = editorService.obtenerTodosLosEditores();
        List<Editor> editores = editorService.obtenerCategoriaPorEstado(1);
        return editores;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Editor> obtenerEditorPorId(@PathVariable Long id) {
        Editor editor = editorService.obtenerEditorPorId(id);
        return ResponseEntity.ok(editor);
    }

    @PostMapping
    public ResponseEntity<Editor> crearEditor(@RequestBody Editor editor) {
        Editor nuevoEditor = editorService.crearEditor(editor);
        return new ResponseEntity<>(nuevoEditor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Editor> actualizarEditor(@PathVariable Long id, @RequestBody Editor editorActualizado) {
        Editor editorAct = editorService.actualizarEditor(id, editorActualizado);
        return ResponseEntity.ok(editorAct);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Boolean> eliminarLogico(@PathVariable Long id){
        if(editorService.eliminarLogico(id)){
            return new ResponseEntity<>(true,HttpStatus.OK);
        }
        return new ResponseEntity<>(false   ,HttpStatus.NOT_FOUND);
    }
}
