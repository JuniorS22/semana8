package com.codigo.semana8.service;

import com.codigo.semana8.model.Editor;
import com.codigo.semana8.repository.EditorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EditorService {

    private final EditorRepository editorRepository;


    public EditorService(EditorRepository editorRepository) {
        this.editorRepository = editorRepository;
    }

    public List<Editor> obtenerTodosLosEditores() {
        return editorRepository.findAll();
    }

    public Editor obtenerEditorPorId(Long id) {
        Optional<Editor> editor = editorRepository.findById(id);
        if (editor.isPresent()) {
            return editor.get();
        } else {
            throw new RuntimeException("Editor no encontrado con ID: " + id);
        }
    }

    public Editor crearEditor(Editor editor) {
        return editorRepository.save(editor);
    }

    public Editor actualizarEditor(Long id, Editor editorActualizado) {
        Editor editorExistente = obtenerEditorPorId(id);

        editorExistente.setNombre(editorActualizado.getNombre());
        editorExistente.setEstado(editorActualizado.getEstado());

        return editorRepository.save(editorExistente);
    }

    public List<Editor> obtenerCategoriaPorEstado(int estado){
        return editorRepository.findByEstado(estado);
    }

    public boolean eliminarLogico(Long id){
        Editor editorExistente=obtenerEditorPorId(id);
        if (editorExistente.getEstado()==1){
            editorExistente.setEstado(0);
            editorRepository.save(editorExistente);
            return true;
        }
        return false;
    }
}
