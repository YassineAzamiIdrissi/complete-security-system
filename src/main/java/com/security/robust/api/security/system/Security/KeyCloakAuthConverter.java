package com.security.robust.api.security.system.Security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

public class KeyCloakAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    @Override
    public AbstractAuthenticationToken convert(@NonNull Jwt source) {
        return new JwtAuthenticationToken(
                source,
                Stream.concat(new JwtGrantedAuthoritiesConverter().convert(source).
                                stream(), getAuthorities(source).stream()).
                        collect(toSet())
        );
    }

    Collection<? extends GrantedAuthority> getAuthorities
            (Jwt kcJwtService) {
        var resource_access = new HashMap<>(kcJwtService.getClaim("resource_access"));
        var innerProps = (Map<String, List<String>>) resource_access.get("account");
        var roles = innerProps.get("roles");
        return roles.stream().
                map(role -> new SimpleGrantedAuthority(
                        "Role_" + role.replace("-", "_")
                )).
                collect(Collectors.toSet());
    }
}
