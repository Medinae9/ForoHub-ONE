package com.alura.forohub.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    Page<Topico> findByActivoTrue(Pageable pageable);

    @Query("SELECT t FROM topico t WHERE (:titulo IS NULL OR t.titulo LIKE %:titulo%) AND (:anio IS NULL OR YEAR(t.fecha) = :anio)")
    Page<Topico> buscarPorTituloYAnio(@Param("titulo") String titulo, @Param("anio") Integer anio, Pageable pageable);

    boolean existsByTitulo(String titulo);
    boolean existsByMensaje(String mensaje);
}