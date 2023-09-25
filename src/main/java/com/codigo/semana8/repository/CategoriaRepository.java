package com.codigo.semana8.repository;

import com.codigo.semana8.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Long> {

    List<Categoria> findByEstado(int estado);
}
