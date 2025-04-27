package com.amilton.gerenciar.domain.service;

import com.amilton.gerenciar.domain.model.Usuario;
import com.amilton.gerenciar.domain.respository.UsuarioRepository;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario cadastrarNovoUsuario(String email, OAuth2User oAuth2User) {
        Usuario novoUsuario = new Usuario();
        novoUsuario.setEmail(email);
        novoUsuario.setNome(oAuth2User.getAttribute("name"));
        novoUsuario.setAuthProvider("GOOGLE");
        novoUsuario.setImagemUrl(oAuth2User.getAttribute("picture"));
        novoUsuario.setSenha(UUID.randomUUID().toString());

        return usuarioRepository.save(novoUsuario);
    }
}
