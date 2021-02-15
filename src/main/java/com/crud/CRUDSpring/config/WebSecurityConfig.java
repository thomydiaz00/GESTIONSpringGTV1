package com.crud.CRUDSpring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

import com.crud.CRUDSpring.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // Necesario para evitar que la seguridad se aplique a los resources
    // Como los css, imagenes y javascripts
    String[] resources = new String[] { "/include/**", "/css/**", "/icons/**", "/img/**", "/js/**", "/layer/**" };

    @Autowired
    AuthenticationSuccessHandler successHandler;

    // para permitir una url tipo /profesor/redirect/*/* hay que poner /profesor/**
    // Temporal hago publico consultar asis
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(resources).permitAll()
                .antMatchers("/", "/index", "/login_profesor", "/profesor/*", "/admin/consultar_asistencia/**",
                        "/profesor/redirect_to_horarios/**", "/profesor/save_asistencia")
                .permitAll().antMatchers("/admin/lista_profesores").access("hasRole('ADMIN')")
                .antMatchers("/admin/prueba").access("hasRole('ADMIN')").antMatchers("/admin/lista_clases")
                .access("hasRole('ADMIN')").antMatchers("/admin/lista_profesores_completa").access("hasRole('ADMIN')")
                .anyRequest().authenticated().and().formLogin().loginPage("/login").successHandler(successHandler)
                .permitAll().failureUrl("/login?error=true").usernameParameter("username").passwordParameter("password")
                .and().logout().permitAll().logoutSuccessUrl("/login?logout");
    }

    BCryptPasswordEncoder bCryptPasswordEncoder;

    // Crea el encriptador de contraseñas
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
        // El numero 4 representa que tan fuerte quieres la encriptacion.
        // Se puede en un rango entre 4 y 31.
        // Si no pones un numero el programa utilizara uno aleatoriamente cada vez
        // que inicies la aplicacion, por lo cual tus contrasenas encriptadas no
        // funcionaran bien
        return bCryptPasswordEncoder;
    }

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    // Registra el service para usuarios y el encriptador de contrasena
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        // Setting Service to find User in the database.
        // And Setting PassswordEncoder
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}