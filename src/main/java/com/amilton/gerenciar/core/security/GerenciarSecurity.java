package com.amilton.gerenciar.core.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;

@Component
public class GerenciarSecurity {

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public Long getUsuarioId() {
        var oidcUser = (OidcUser) getAuthentication().getPrincipal();
        Object usuarioId = oidcUser.getAttribute("usuario_id");

        if (usuarioId == null) {
            return null;
        }

        return Long.valueOf(usuarioId.toString());
    }
}
