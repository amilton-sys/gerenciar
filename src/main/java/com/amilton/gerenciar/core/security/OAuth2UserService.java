package com.amilton.gerenciar.core.security;

import com.amilton.gerenciar.domain.model.Usuario;
import com.amilton.gerenciar.domain.respository.UsuarioRepository;
import com.amilton.gerenciar.domain.service.UsuarioService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OAuth2UserService extends OidcUserService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioService usuarioService;

    public OAuth2UserService(UsuarioRepository usuarioRepository, UsuarioService usuarioService) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioService = usuarioService;
    }

    @Transactional
    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oAuth2User = super.loadUser(userRequest);
        String email = oAuth2User.getAttribute("email");

        if (email == null) {
            throw new OAuth2AuthenticationException("Email não encontrado.");
        }

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseGet(() -> usuarioService.cadastrar(email, oAuth2User));

        Collection<GrantedAuthority> authorities = getAuthorities(usuario);

        Map<String, Object> customAttributes = new HashMap<>(oAuth2User.getClaims());

        customAttributes.put("usuario_id", usuario.getId());

        return new DefaultOidcUser(
                authorities,
                oAuth2User.getIdToken(),
                oAuth2User.getUserInfo(),
                "email"
        ){
            @Override
            public Map<String, Object> getAttributes() {
                return customAttributes;
            }

            @Override
            public <A> A getAttribute(String name) {
                return (A) customAttributes.get(name);
            }
        };
    }

    private Collection<GrantedAuthority> getAuthorities(Usuario usuario) {
        return usuario.getGrupos().stream()
                .flatMap(grupo -> grupo.getPermissoes().stream())
                .map(permissao -> new SimpleGrantedAuthority(permissao.getNome()))
                .collect(Collectors.toSet());
    }
}
