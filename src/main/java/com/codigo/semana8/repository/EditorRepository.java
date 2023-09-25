package com.codigo.semana8.repository;

import com.codigo.semana8.model.Editor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EditorRepository extends JpaRepository<Editor,Long> {

    List<Editor> findByEstado(int estado);
}
