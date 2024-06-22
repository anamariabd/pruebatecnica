package com.test.pruebatecnica.repository;

import com.test.pruebatecnica.model.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;

public interface UsuarioRepository extends CrudRepository<Usuario, BigDecimal> {

}