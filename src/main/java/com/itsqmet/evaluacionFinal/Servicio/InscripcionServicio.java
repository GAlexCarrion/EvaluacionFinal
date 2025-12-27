package com.itsqmet.evaluacionFinal.Servicio;

import com.itsqmet.evaluacionFinal.Entidad.Curso;
import com.itsqmet.evaluacionFinal.Entidad.Inscripcion;
import com.itsqmet.evaluacionFinal.Entidad.Usuario;
import com.itsqmet.evaluacionFinal.Repositorio.InscripcionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class InscripcionServicio {

    @Autowired
    private InscripcionRepositorio inscripcionRepositorio;

    public List<Inscripcion> findByEstudianteId(Long estudianteId) {
        return inscripcionRepositorio.findByEstudianteId(estudianteId);
    }

    public Inscripcion inscribirEstudiante(Usuario estudiante, Curso curso) {
        // Verificar si ya está inscrito
        if (inscripcionRepositorio.existsByEstudianteIdAndCursoId(estudiante.getId(), curso.getId())) {
            throw new RuntimeException("Ya estás inscrito en este curso");
        }

        // Verificar cupo disponible
        int inscritos = inscripcionRepositorio.countByCursoId(curso.getId());
        if (inscritos >= curso.getCupo()) {
            throw new RuntimeException("No hay cupos disponibles");
        }

        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setEstudiante(estudiante);
        inscripcion.setCurso(curso);
        inscripcion.setFecha(LocalDate.now());

        return inscripcionRepositorio.save(inscripcion);
    }

    public void eliminarInscripcion(Long id) {
        inscripcionRepositorio.deleteById(id);
    }
}