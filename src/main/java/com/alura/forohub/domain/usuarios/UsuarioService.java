package com.alura.forohub.domain.usuarios;

import com.alura.forohub.domain.perfiles.Perfil;
import com.alura.forohub.domain.perfiles.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario registrarUsuario(DatosRegistroUsuario datos) {
        if (usuarioRepository.existsByCorreoElectronico(datos.correoElectronico())) {
            throw new RuntimeException("⚠️ El correo electrónico ya está registrado.");
        }
        String contrasenaEncode = passwordEncoder.encode(datos.contrasena());
        Perfil perfil = perfilRepository.findById(3L)
                .orElseThrow(() -> new RuntimeException("Perfil por defecto no encontrado"));
        Usuario nuevoUsuario = new Usuario(datos, perfil, contrasenaEncode);
        return usuarioRepository.save(nuevoUsuario);
    }
}