package com.security.robust.api.security.system.General;

import com.security.robust.api.security.system.AppAuditing.ApplicationAuditorAware;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;

import static org.springframework.http.HttpHeaders.*;

@Configuration
@RequiredArgsConstructor
public class BeansConfiguration {
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(
                Collections.singletonList(
                        "http://localhost:4200"
                )
        );
        config.setAllowedHeaders(
                Arrays.asList(
                        ORIGIN,
                        CONTENT_TYPE,
                        AUTHORIZATION,
                        ACCEPT
                )
        );
        config.setAllowedMethods(
                Arrays.asList(
                        "GET",
                        "POST",
                        "PUT",
                        "DELETE",
                        "PATCH"
                )
        );
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Bean
    public ApplicationAuditorAware applicationAuditorAware() {
        return new ApplicationAuditorAware();
    }
}
