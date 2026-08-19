package com.camiones.service;

import com.camiones.dto.CamionRequest;
import com.camiones.entity.Camion;
import com.camiones.entity.Conductor;
import com.camiones.repository.CamionRepository;
import com.camiones.repository.ConductorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service @RequiredArgsConstructor
public class CamionService {
    private final CamionRepository camionRepository;
    private final ConductorRepository conductorRepository;

    public List<Camion> listar() { return camionRepository.findAll(); }
    public Camion buscar(Long id) { return camionRepository.findById(id).orElseThrow(() -> new RuntimeException("Camión no encontrado")); }
    public Camion crear(CamionRequest r) { return camionRepository.save(Camion.builder().placa(r.placa()).marca(r.marca()).modelo(r.modelo()).activo(r.activo()).build()); }
    public Camion actualizar(Long id, CamionRequest r) { Camion c=buscar(id); c.setPlaca(r.placa()); c.setMarca(r.marca()); c.setModelo(r.modelo()); c.setActivo(r.activo()); return camionRepository.save(c); }
    public void eliminar(Long id) { camionRepository.delete(buscar(id)); }
    public Camion asociar(Long camionId, Long conductorId) {
        Camion c=buscar(camionId);
        Conductor d=conductorRepository.findById(conductorId).orElseThrow(() -> new RuntimeException("Conductor no encontrado"));
        if (!d.isActivo()) throw new RuntimeException("El conductor está inactivo");
        c.setConductor(d);
        return camionRepository.save(c);
    }
    public Camion desasociar(Long camionId) { Camion c=buscar(camionId); c.setConductor(null); return camionRepository.save(c); }
}
