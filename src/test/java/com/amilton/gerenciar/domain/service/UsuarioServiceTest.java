package com.amilton.gerenciar.domain.service;

import com.amilton.gerenciar.domain.model.Usuario;
import com.amilton.gerenciar.domain.respository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.oauth2.core.user.OAuth2User;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    void cadastrar() {
        OAuth2User oAuth2User = Mockito.mock(OAuth2User.class);
        Mockito.when(oAuth2User.getAttribute("name")).thenReturn("Nome do Usuário");
        Mockito.when(oAuth2User.getAttribute("picture")).thenReturn("URL da Imagem");

        Mockito.when(usuarioRepository.save(Mockito.any(Usuario.class))).thenAnswer(i -> i.getArgument(0));

        String email = "amiltonjose.pe@gmail.com";
        Usuario usuario = usuarioService.cadastrar(email, oAuth2User);
        Usuario usuario = usuarioService.cadastrarNovoUsuario(email, oAuth2User);
        assertNotNull(usuario);
        assertEquals(email, usuario.getEmail());
        assertEquals("Nome do Usuário", usuario.getNome());
        assertEquals("GOOGLE", usuario.getAuthProvider());
        assertEquals("URL da Imagem", usuario.getImagemUrl());
    }
}
