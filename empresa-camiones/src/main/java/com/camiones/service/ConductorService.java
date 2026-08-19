package com.camiones.service;
import com.camiones.dto.ConductorRequest;
import com.camiones.entity.Conductor;
import com.camiones.repository.ConductorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
@Service @RequiredArgsConstructor
public class ConductorService {
 private final ConductorRepository repo;
 public List<Conductor> listar(){return repo.findAll();}
 public Conductor buscar(Long id){return repo.findById(id).orElseThrow(()->new RuntimeException("Conductor no encontrado"));}
 public Conductor crear(ConductorRequest r){return repo.save(Conductor.builder().nombre(r.nombre()).documento(r.documento()).licencia(r.licencia()).activo(r.activo()).build());}
 public Conductor actualizar(Long id, ConductorRequest r){Conductor c=buscar(id);c.setNombre(r.nombre());c.setDocumento(r.documento());c.setLicencia(r.licencia());c.setActivo(r.activo());return repo.save(c);}
 public void eliminar(Long id){repo.delete(buscar(id));}
}
