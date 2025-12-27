package com.itsqmet.evaluacionFinal.Servicio;

import com.itsqmet.evaluacionFinal.Entidad.Curso;
import com.itsqmet.evaluacionFinal.Repositorio.CursoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoServicio {

    @Autowired
    private CursoRepositorio cursoRepositorio;

    public List<Curso> findAll() {
        return cursoRepositorio.findAll();
    }

    public Optional<Curso> findById(Long id) {
        return cursoRepositorio.findById(id);
    }

    public Curso save(Curso curso) {
        return cursoRepositorio.save(curso);
    }

    public void deleteById(Long id) {
        cursoRepositorio.deleteById(id);
    }

    public List<Curso> buscarPorNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            return cursoRepositorio.findAll();
        }
        return cursoRepositorio.findByNombreContainingIgnoreCase(nombre);
    }

    public List<Curso> findByDocenteId(Long docenteId) {
        return cursoRepositorio.findByDocenteId(docenteId);
    }
}