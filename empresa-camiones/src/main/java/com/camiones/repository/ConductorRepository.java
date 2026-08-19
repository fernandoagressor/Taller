package com.camiones.repository;
import com.camiones.entity.Conductor;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ConductorRepository extends JpaRepository<Conductor,Long> { }
