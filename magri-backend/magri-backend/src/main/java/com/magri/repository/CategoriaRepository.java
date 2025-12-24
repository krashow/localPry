package com.magri.repository;

import com.magri.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository; // O CrudRepository, si lo prefieres
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> { 
}