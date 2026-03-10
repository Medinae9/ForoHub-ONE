package com.alura.forohub.controller;

import com.alura.forohub.domain.usuarios.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<DatosDetalleUsuario> registrar(@RequestBody @Valid DatosRegistroUsuario datos,
                                                         UriComponentsBuilder uriBuilder) {
        Usuario usuario = usuarioService.registrarUsuario(datos);
        var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosDetalleUsuario(usuario));
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaUsuarios>> listar(
            @PageableDefault(size = 10, sort = "nombre") Pageable pageable) {
        var page = usuarioRepository.findAllByActivoTrue(pageable).map(DatosListaUsuarios::new);
        return ResponseEntity.ok(page);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        var usuario = usuarioRepository.findById(id);
        if (usuario.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        usuario.get().eliminar();
        return ResponseEntity.noContent().build();
    }
}