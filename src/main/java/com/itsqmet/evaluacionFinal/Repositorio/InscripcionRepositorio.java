package com.itsqmet.evaluacionFinal.Repositorio;

import com.itsqmet.evaluacionFinal.Entidad.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InscripcionRepositorio extends JpaRepository<Inscripcion, Long> {
    List<Inscripcion> findByEstudianteId(Long estudianteId);
    boolean existsByEstudianteIdAndCursoId(Long estudianteId, Long cursoId);
    int countByCursoId(Long cursoId);
}