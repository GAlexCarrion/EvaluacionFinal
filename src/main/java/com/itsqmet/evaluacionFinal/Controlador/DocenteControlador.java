package com.itsqmet.evaluacionFinal.Controlador;

import com.itsqmet.evaluacionFinal.Entidad.Curso;
import com.itsqmet.evaluacionFinal.Entidad.Usuario;
import com.itsqmet.evaluacionFinal.Servicio.CursoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/docente")
public class DocenteControlador {

    @Autowired
    private CursoServicio cursoServicio;

    @GetMapping("/cursos")
    public String misCursos(Model model, Authentication authentication) {
        String username = authentication.getName();
        // En una implementación real, buscaríamos el usuario por username
        // y luego sus cursos
        List<Curso> cursos = cursoServicio.findAll(); // Temporal
        model.addAttribute("cursos", cursos);
        return "docente/cursos";
    }

    @GetMapping("/curso/nuevo")
    public String nuevoCurso(Model model) {
        model.addAttribute("curso", new Curso());
        return "docente/form-curso";
    }

    @PostMapping("/curso/guardar")
    public String guardarCurso(@ModelAttribute Curso curso, Authentication authentication) {
        // Asignar el docente actual al curso
        // En implementación real: buscar usuario y asignarlo
        cursoServicio.save(curso);
        return "redirect:/docente/cursos";
    }

    @GetMapping("/curso/editar/{id}")
    public String editarCurso(@PathVariable Long id, Model model) {
        cursoServicio.findById(id).ifPresent(curso -> model.addAttribute("curso", curso));
        return "docente/form-curso";
    }
}