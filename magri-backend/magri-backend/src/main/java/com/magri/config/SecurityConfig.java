package com.magri.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import java.util.List;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        	.csrf(AbstractHttpConfigurer::disable)
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.GET, "/api/incidencias/documentos/*").permitAll() 
                .requestMatchers(HttpMethod.GET, "/api/incidencias/documentos/descargar/*").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/incidencias/upload-documento").permitAll()		
                //.requestMatchers("/", "/index.html", "/assets/**", "/favicon.ico").permitAll() 
                .requestMatchers(HttpMethod.GET, "/", "/index.html", "/assets/**", "/*.html", "/*.css", "/*.js", "/*.svg", "/*.png", "/*.jpg", "/*.ico").permitAll()
                .requestMatchers("/api/usuarios/responsables", "/api/incidencias/detalle").permitAll() 
            	.requestMatchers(HttpMethod.POST, "/api/incidencias/asignar-responsable").permitAll() 
                .requestMatchers(HttpMethod.GET, "/api/usuarios/responsables").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/incidencias/detalle").permitAll()
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/prioridades").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/incidencias/responsable/asignadas").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/incidencias/*/seguimientos").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/incidencias/*/seguimientos").permitAll()             
                .requestMatchers(HttpMethod.POST, "/api/gestion/registrar").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/gestion/historial/*").permitAll()               
                // === REGLAS AMPLIAS ===
                .requestMatchers("/api/incidencias/**").permitAll()
                //.requestMatchers("/api/usuarios/**").authenticated() 
                //.anyRequest().authenticated()
                .requestMatchers("/api/usuarios/**").permitAll()
                .anyRequest().permitAll()
            )
            .formLogin(form -> form.disable())
            .httpBasic(basic -> basic.disable());

        return http.build();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(List.of( 
            "http://localhost:4200", 
            "http://localhost:5173", 
            "http://127.0.0.1:5500", 
            "http://localhost:8080", 
            "http://localhost:8081",
            "http://192.168.1.116:8081",
            "http://192.168.1.116",  
            "http://192.168.1.104", 
            "http://192.168.1.104:8081"
        ));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
