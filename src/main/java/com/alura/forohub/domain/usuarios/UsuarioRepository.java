package com.alura.forohub.domain.usuarios;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByCorreoElectronico(String correoElectronico);
    UserDetails findByCorreoElectronico(String correo);
    Page<Usuario> findAllByActivoTrue(Pageable pageable);
}