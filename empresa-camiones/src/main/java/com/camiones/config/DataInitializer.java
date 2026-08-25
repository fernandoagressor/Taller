package com.camiones.config;

import com.camiones.entity.*;
import com.camiones.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner init(
            UsuarioRepository usuarios,
            ConductorRepository conductores,
            CamionRepository camiones) {

        return args -> {
            if (usuarios.count() == 0) {
                usuarios.save(Usuario.builder()
                        .username("admin")
                        .password("123456")
                        .rol(Rol.ADMIN)
                        .activo(true)
                        .build());

                usuarios.save(Usuario.builder()
                        .username("supervisor")
                        .password("123456")
                        .rol(Rol.SUPERVISOR)
                        .activo(true)
                        .build());
            }

            if (conductores.count() == 0) {
                conductores.save(Conductor.builder()
                        .nombre("Carlos Pérez")
                        .documento("1001001001")
                        .licencia("C3")
                        .activo(true)
                        .build());

                conductores.save(Conductor.builder()
                        .nombre("Ana Gómez")
                        .documento("1001001002")
                        .licencia("C3")
                        .activo(true)
                        .build());
            }

            if (camiones.count() == 0) {
                camiones.save(Camion.builder()
                        .placa("ABC123")
                        .marca("Volvo")
                        .modelo("FH 2024")
                        .activo(true)
                        .build());

                camiones.save(Camion.builder()
                        .placa("XYZ789")
                        .marca("Scania")
                        .modelo("R 2023")
                        .activo(true)
                        .build());
            }
        };
    }
}
