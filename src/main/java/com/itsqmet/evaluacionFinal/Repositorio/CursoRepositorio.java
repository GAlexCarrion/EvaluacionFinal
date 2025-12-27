package com.itsqmet.evaluacionFinal.Repositorio;

import com.itsqmet.evaluacionFinal.Entidad.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursoRepositorio extends JpaRepository<Curso, Long> {
    List<Curso> findByNombreContainingIgnoreCase(String nombre);
    List<Curso> findByDocenteId(Long docenteId);
}