package com.camiones.controller;
import com.camiones.entity.*; import com.camiones.service.*; import lombok.RequiredArgsConstructor; import org.springframework.web.bind.annotation.*; import java.util.List;
@RestController @RequestMapping("/api/supervisor") @RequiredArgsConstructor
public class SupervisorController {
 private final CamionService camiones; private final ConductorService conductores;
 @GetMapping("/camiones") public List<Camion> camiones(){return camiones.listar();}
 @GetMapping("/conductores") public List<Conductor> conductores(){return conductores.listar();}
 @PostMapping("/camiones/{camionId}/conductor/{conductorId}") public Camion asociar(@PathVariable Long camionId,@PathVariable Long conductorId){return camiones.asociar(camionId,conductorId);}
 @DeleteMapping("/camiones/{camionId}/conductor") public Camion desasociar(@PathVariable Long camionId){return camiones.desasociar(camionId);}
}
