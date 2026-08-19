package com.camiones.controller;
import com.camiones.dto.*; import com.camiones.entity.*; import com.camiones.service.*;
import jakarta.validation.Valid; import lombok.RequiredArgsConstructor; import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.util.List;
@RestController @RequestMapping("/api/admin") @RequiredArgsConstructor
public class AdminController {
 private final UsuarioService usuarios; private final CamionService camiones; private final ConductorService conductores;
 @GetMapping("/usuarios") public List<Usuario> usuarios(){return usuarios.listar();}
 @PostMapping("/usuarios") public ResponseEntity<Usuario> crearUsuario(@Valid @RequestBody UsuarioRequest r){return ResponseEntity.status(HttpStatus.CREATED).body(usuarios.crear(r));}
 @DeleteMapping("/usuarios/{id}") public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id){usuarios.eliminar(id);return ResponseEntity.noContent().build();}
 @GetMapping("/camiones") public List<Camion> camiones(){return camiones.listar();}
 @PostMapping("/camiones") public ResponseEntity<Camion> crearCamion(@Valid @RequestBody CamionRequest r){return ResponseEntity.status(HttpStatus.CREATED).body(camiones.crear(r));}
 @PutMapping("/camiones/{id}") public Camion actualizarCamion(@PathVariable Long id,@Valid @RequestBody CamionRequest r){return camiones.actualizar(id,r);}
 @DeleteMapping("/camiones/{id}") public ResponseEntity<Void> eliminarCamion(@PathVariable Long id){camiones.eliminar(id);return ResponseEntity.noContent().build();}
 @GetMapping("/conductores") public List<Conductor> conductores(){return conductores.listar();}
 @PostMapping("/conductores") public ResponseEntity<Conductor> crearConductor(@Valid @RequestBody ConductorRequest r){return ResponseEntity.status(HttpStatus.CREATED).body(conductores.crear(r));}
 @PutMapping("/conductores/{id}") public Conductor actualizarConductor(@PathVariable Long id,@Valid @RequestBody ConductorRequest r){return conductores.actualizar(id,r);}
 @DeleteMapping("/conductores/{id}") public ResponseEntity<Void> eliminarConductor(@PathVariable Long id){conductores.eliminar(id);return ResponseEntity.noContent().build();}
}
