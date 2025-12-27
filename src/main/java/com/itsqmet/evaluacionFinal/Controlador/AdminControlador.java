package com.itsqmet.evaluacionFinal.Controlador;

import com.itsqmet.evaluacionFinal.Entidad.Curso;
import com.itsqmet.evaluacionFinal.Entidad.Usuario;
import com.itsqmet.evaluacionFinal.Servicio.CursoServicio;
import com.itsqmet.evaluacionFinal.Servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private CursoServicio cursoServicio;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        List<Usuario> usuarios = usuarioServicio.findAll();
        List<Curso> cursos = cursoServicio.findAll();

        model.addAttribute("usuarios", usuarios);
        model.addAttribute("cursos", cursos);
        model.addAttribute("totalUsuarios", usuarios.size());
        model.addAttribute("totalCursos", cursos.size());

        return "admin/dashboard";
    }

    @GetMapping("/usuarios")
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioServicio.findAll());
        return "admin/usuarios";
    }

    @GetMapping("/cursos")
    public String listarCursos(Model model, @RequestParam(required = false) String buscar) {
        List<Curso> cursos = cursoServicio.buscarPorNombre(buscar);
        model.addAttribute("cursos", cursos);
        model.addAttribute("buscar", buscar);
        return "admin/cursos";
    }

    @GetMapping("/curso/nuevo")
    public String nuevoCurso(Model model) {
        model.addAttribute("curso", new Curso());
        model.addAttribute("docentes", usuarioServicio.findByRol("DOCENTE"));
        return "admin/form-curso";
    }

    @PostMapping("/curso/guardar")
    public String guardarCurso(@ModelAttribute Curso curso) {
        cursoServicio.save(curso);
        return "redirect:/admin/cursos";
    }

    @GetMapping("/curso/editar/{id}")
    public String editarCurso(@PathVariable Long id, Model model) {
        cursoServicio.findById(id).ifPresent(curso -> model.addAttribute("curso", curso));
        model.addAttribute("docentes", usuarioServicio.findByRol("DOCENTE"));
        return "admin/form-curso";
    }

    @GetMapping("/curso/eliminar/{id}")
    public String eliminarCurso(@PathVariable Long id) {
        cursoServicio.deleteById(id);
        return "redirect:/admin/cursos";
    }

    @GetMapping("/usuario/nuevo")
    public String nuevoUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "admin/form-usuario";
    }

    @PostMapping("/usuario/guardar")
    public String guardarUsuario(@ModelAttribute Usuario usuario) {
        usuarioServicio.save(usuario);
        return "redirect:/admin/usuarios";
    }
}