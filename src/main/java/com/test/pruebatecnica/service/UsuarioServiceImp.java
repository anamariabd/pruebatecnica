package com.test.pruebatecnica.service;

import com.test.pruebatecnica.model.Usuario;
import com.test.pruebatecnica.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.Random;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class UsuarioServiceImp implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private static final String ALPHANUM_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789?=()";
    private static final Random random = new Random();

    private Usuario userLogin;

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
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).get();
    }

    @Override
    public boolean authenticate(String email, String password) {
        Usuario user = this.usuarioRepository.findByEmail(email);

        if (user == null ) return false;
        return user.getPassword().equals( password);

    }

    @Override
    public Usuario findByEmail(String email) {
        return this.usuarioRepository.findByEmail(email);
    }

    @Override
    public void setUserLogin(Usuario usuario) {
        this.userLogin = usuario;
    }

    @Override
    public Usuario getUserLogin() {
        return this.userLogin;
    }


    public static String generarCadena(int longitudMinima) {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < longitudMinima; i++){
            int randomIndex = random.nextInt(ALPHANUM_CHARACTERS.length());
            sb.append(ALPHANUM_CHARACTERS.charAt(randomIndex));
        }
        return sb.toString();
    }
}
