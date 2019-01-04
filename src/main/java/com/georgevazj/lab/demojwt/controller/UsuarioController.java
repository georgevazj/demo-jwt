package com.georgevazj.lab.demojwt.controller;

import com.georgevazj.lab.demojwt.model.Usuario;
import com.georgevazj.lab.demojwt.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    private UsuarioRepository usuarioRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UsuarioController(UsuarioRepository usuarioRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Page<Usuario> getUsuarios(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    @RequestMapping(value = "/{usuario}", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Usuario getUsuario(@PathVariable String username) {
        return usuarioRepository.findByUsername(username);
    }

    @RequestMapping(method = RequestMethod.POST,produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Usuario saveUsuario(@Valid @RequestBody Usuario usuario) {
        usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }
}
