package com.itsqmet.evaluacionFinal.Servicio;

import com.itsqmet.evaluacionFinal.Entidad.Usuario;
import com.itsqmet.evaluacionFinal.Repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public List<Usuario> findAll() {
        return usuarioRepositorio.findAll();
    }

    public Optional<Usuario> findById(Long id) {
        return usuarioRepositorio.findById(id);
    }

    public Usuario save(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepositorio.save(usuario);
    }

    public void deleteById(Long id) {
        usuarioRepositorio.deleteById(id);
    }

    public List<Usuario> findByRol(String rol) {
        return usuarioRepositorio.findByRol(rol);
    }
}