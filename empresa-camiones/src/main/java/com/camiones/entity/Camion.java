package com.camiones.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name="camiones")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Camion {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false, unique=true) private String placa;
    @Column(nullable=false) private String marca;
    @Column(nullable=false) private String modelo;
    @Column(nullable=false) private boolean activo;
    @OneToOne
    @JoinColumn(name="conductor_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Conductor conductor;
}
