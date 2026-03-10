package com.alura.forohub.domain.curso;

import jakarta.persistence.*;
import lombok.*;

    @Table(name = "cursos")
    @Entity(name = "curso")
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode(of = "id")
    public class Curso {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(unique = true)
        private String nombre;

        @Enumerated(EnumType.STRING)
        private Categoria categoria;
}
