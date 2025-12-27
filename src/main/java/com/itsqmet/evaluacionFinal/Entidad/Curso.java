package com.itsqmet.evaluacionFinal.Entidad;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "cursos")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private Integer cupo;

    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "docente_id")
    private Usuario docente;
}