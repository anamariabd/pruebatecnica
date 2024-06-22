package com.test.pruebatecnica.service;

import com.test.pruebatecnica.model.Usuario;

import java.math.BigDecimal;

public interface UsuarioService {

    Usuario save(Usuario user);
    void delete(Usuario user);

    Usuario update(Usuario entity);
    public Usuario findById(BigDecimal id);
    boolean authenticate(String email, String password);

    Usuario findByEmail(String email);
}
