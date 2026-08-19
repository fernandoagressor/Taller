package com.camiones.config;
import com.camiones.entity.*;
import com.camiones.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration; import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration @RequiredArgsConstructor
public class DataInitializer {
 @Bean CommandLineRunner init(UsuarioRepository usuarios,ConductorRepository conductores,CamionRepository camiones,PasswordEncoder encoder){return args->{
  if(usuarios.count()==0){usuarios.save(Usuario.builder().username("admin").password(encoder.encode("123456")).rol(Rol.ADMIN).activo(true).build());usuarios.save(Usuario.builder().username("supervisor").password(encoder.encode("123456")).rol(Rol.SUPERVISOR).activo(true).build());}
  Conductor d1=conductores.save(Conductor.builder().nombre("Carlos Pérez").documento("1001001001").licencia("C3").activo(true).build());
  conductores.save(Conductor.builder().nombre("Ana Gómez").documento("1001001002").licencia("C3").activo(true).build());
  camiones.save(Camion.builder().placa("ABC123").marca("Volvo").modelo("FH 2024").activo(true).build());
  camiones.save(Camion.builder().placa("XYZ789").marca("Scania").modelo("R 2023").activo(true).build());
 };}
}
