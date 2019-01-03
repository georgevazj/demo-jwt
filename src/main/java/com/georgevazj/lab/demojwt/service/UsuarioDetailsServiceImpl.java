package com.georgevazj.lab.demojwt.service;

import com.georgevazj.lab.demojwt.model.Usuario;
import com.georgevazj.lab.demojwt.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService {

    private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioDetailsServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(usuario.getUsername(), usuario.getPassword(), emptyList());
    }
}
