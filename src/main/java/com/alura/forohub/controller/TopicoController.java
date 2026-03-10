package com.alura.forohub.controller;

import com.alura.forohub.domain.topico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    public ResponseEntity<DatosDetalleTopico> registrar(@RequestBody @Valid DatosCrearTopico datos,
                                                        UriComponentsBuilder uriBuilder) {
        Topico topico = topicoService.crearTopico(datos);
        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosDetalleTopico(topico));
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaTopicos>> listar(
            @PageableDefault(size = 10, sort = "fecha") Pageable pageable) {
        var page = topicoRepository.findByActivoTrue(pageable).map(DatosListaTopicos::new);
        return ResponseEntity.ok(page);
    }

    @PostMapping("/buscar")
    public ResponseEntity<Page<DatosListaTopicos>> buscar(@RequestBody DatosBusquedaTopicos datos,
                                                          @PageableDefault(size = 10, sort = "fecha") Pageable pageable) {
        var page = topicoRepository.buscarPorTituloYAnio(datos.titulo(), datos.anio(), pageable)
                .map(DatosListaTopicos::new);
        if (page.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosListaTopicos> detalle(@PathVariable Long id) {
        var topico = topicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatosListaTopicos(topico));
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<DatosDetalleTopico> actualizar(@PathVariable Long id,
                                                         @RequestBody @Valid DatosActualizarTopico datos) {
        topicoService.actualizarTopico(id, datos);
        var topico = topicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatosDetalleTopico(topico));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        boolean eliminado = topicoService.eliminarTopico(id);
        if (eliminado) return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }
}