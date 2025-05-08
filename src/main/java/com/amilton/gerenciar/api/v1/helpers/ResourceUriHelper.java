package com.amilton.gerenciar.api.v1.helpers;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class ResourceUriHelper {
    public static URI buildUri(Object id) {
        return ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }
}
