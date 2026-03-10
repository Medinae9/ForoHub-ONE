package com.alura.forohub.domain.topico;

import com.alura.forohub.domain.curso.Curso;
import com.alura.forohub.domain.curso.CursoRepository;
import com.alura.forohub.domain.usuarios.Usuario;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public Topico crearTopico(DatosCrearTopico datos) {
        if (topicoRepository.existsByTitulo(datos.titulo())) {
            throw new jakarta.validation.ValidationException("⚠️ Ya existe un tópico con este título.");
        }
        if (topicoRepository.existsByMensaje(datos.mensaje())) {
            throw new jakarta.validation.ValidationException("⚠️ Ya existe un tópico con este mensaje.");
        }
        var autor = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Curso curso = cursoRepository.findById(datos.cursoId())
                .orElseThrow(() -> new EntityNotFoundException("Curso no encontrado"));
        return topicoRepository.save(new Topico(datos, curso, autor));
    }

    public void actualizarTopico(Long id, DatosActualizarTopico datos) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico no encontrado"));
        if (datos.cursoId() != null) {
            Curso curso = cursoRepository.findById(datos.cursoId())
                    .orElseThrow(() -> new EntityNotFoundException("Curso no encontrado"));
            topico.setCurso(curso);
        }
        topico.actualizarInformacion(datos);
    }

    public boolean eliminarTopico(Long id) {
        var topico = topicoRepository.findById(id);
        if (topico.isPresent()) {
            topico.get().eliminar();
            return true;
        }
        return false;
    }
}