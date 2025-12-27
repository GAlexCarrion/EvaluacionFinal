package com.itsqmet.evaluacionFinal.Controlador;

import com.itsqmet.evaluacionFinal.Entidad.Curso;
import com.itsqmet.evaluacionFinal.Entidad.Inscripcion;
import com.itsqmet.evaluacionFinal.Entidad.Usuario;
import com.itsqmet.evaluacionFinal.Servicio.CursoServicio;
import com.itsqmet.evaluacionFinal.Servicio.InscripcionServicio;
import com.itsqmet.evaluacionFinal.Servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/estudiante")
public class EstudianteControlador {

    @Autowired
    private CursoServicio cursoServicio;

    @Autowired
    private InscripcionServicio inscripcionServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/miscursos")
    public String misCursos(Model model, Authentication authentication) {
        String username = authentication.getName();
        // Buscar usuario y sus inscripciones
        // Temporal: mostrar todos los cursos
        List<Curso> cursos = cursoServicio.findAll();
        model.addAttribute("cursos", cursos);
        return "estudiante/miscursos";
    }

    @GetMapping("/cursos")
    public String listarCursos(Model model, @RequestParam(required = false) String buscar) {
        List<Curso> cursos = cursoServicio.buscarPorNombre(buscar);
        model.addAttribute("cursos", cursos);
        model.addAttribute("buscar", buscar);
        return "estudiante/cursos-disponibles";
    }

    @GetMapping("/inscribir/{cursoId}")
    public String inscribirCurso(@PathVariable Long cursoId, Authentication authentication) {
        String username = authentication.getName();

        // En implementación real:
        // 1. Buscar estudiante por username
        // 2. Buscar curso por ID
        // 3. Inscribir al estudiante

        return "redirect:/estudiante/miscursos";
    }
}