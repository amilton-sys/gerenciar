package com.amilton.gerenciar.domain.service;

import com.amilton.gerenciar.domain.model.Usuario;
import com.amilton.gerenciar.domain.respository.UsuarioRepository;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario buscar(final Long usuarioId){
        return usuarioRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuario de %s não encontrado".formatted(usuarioId)));
    }

    public Usuario buscarPorEmail(String email){
        return usuarioRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuario de %s não encontrado".formatted(email)));
    }

    @Transactional
    public Usuario cadastrar(String email, OAuth2User oAuth2User) {
        Usuario novoUsuario = new Usuario();
        novoUsuario.setEmail(email);
        novoUsuario.setNome(oAuth2User.getAttribute("name"));
        novoUsuario.setAuthProvider("GOOGLE");
        novoUsuario.setImagemUrl(oAuth2User.getAttribute("picture"));
        novoUsuario.setSenha(UUID.randomUUID().toString());

        return usuarioRepository.save(novoUsuario);
    }
}
