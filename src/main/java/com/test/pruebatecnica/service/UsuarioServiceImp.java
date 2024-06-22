package com.test.pruebatecnica.service;

import com.test.pruebatecnica.model.Usuario;
import com.test.pruebatecnica.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UsuarioServiceImp implements UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario save(Usuario user) {
        return null;
    }

    @Override
    public void delete(Usuario user) {

    }

    @Override
    public Usuario update(Usuario entity) {
        return null;
    }

    @Override
    public Usuario findById(BigDecimal id) {
        return null;
    }

    @Override
    public boolean authenticate(String email, String password) {
        return false;
    }

    @Override
    public Usuario findByEmail(String email) {
        return null;
    }
}
