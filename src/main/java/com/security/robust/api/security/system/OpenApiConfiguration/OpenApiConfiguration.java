package com.security.robust.api.security.system.OpenApiConfiguration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Yassine Azami Idrissi",
                        email = "yassineazami0@gmail.com",
                        url = "https://no_domain_currently.com"
                ),
                description = "OpenApi documentation for this Security System",
                title = "OpenApi Specification - Yassine Azami Idrissi",
                version = "1.0",
                license = @License(
                        name = "name",
                        url = "url of this license"
                ),
                termsOfService = "terms of service"
        ),
        servers = {
                @Server(
                        description = "Local environment",
                        url = "http://localhost:8088/api/v1"
                ),
                @Server(
                        description = "production env",
                        url = "https://no_domain_currently.com"
                )
        },
        security = @SecurityRequirement(
                name = "bearerAuth"
        )
)
@SecurityScheme(
        name = "bearerAuth",
        description = "Json Web Token",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfiguration {

}

