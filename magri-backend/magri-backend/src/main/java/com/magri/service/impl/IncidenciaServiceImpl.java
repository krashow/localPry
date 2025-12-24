package com.magri.service.impl;

import com.magri.dto.IncidenciaRequest;
import com.magri.entity.Categoria;
import com.magri.entity.Estado;
import com.magri.entity.Incidencia;
import com.magri.entity.Usuario;
import com.magri.entity.Area;
import com.magri.entity.Prioridad; 
import com.magri.entity.Seguimiento;

import com.magri.repository.CategoriaRepository;
import com.magri.repository.IncidenciaRepository;
import com.magri.repository.EstadoRepository;
import com.magri.repository.UsuarioRepository;
import com.magri.repository.AreaRepository;
import com.magri.repository.PrioridadRepository;
import com.magri.repository.SeguimientoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import com.magri.service.IncidenciaService;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class IncidenciaServiceImpl implements IncidenciaService {

    private final IncidenciaRepository incidenciaRepository;
    private final CategoriaRepository categoriaRepository;
    private final EstadoRepository estadoRepository;
    private final PrioridadRepository prioridadRepository; 

    @Autowired
    private UsuarioRepository usuarioRepository; 
    
    @Autowired
    private AreaRepository areaRepository;
    
    @Autowired
    private SeguimientoRepository seguimientoRepository; 

    public IncidenciaServiceImpl(IncidenciaRepository incidenciaRepository, 
            CategoriaRepository categoriaRepository,
            EstadoRepository estadoRepository,
            PrioridadRepository prioridadRepository) {
				this.incidenciaRepository = incidenciaRepository;
				this.categoriaRepository = categoriaRepository;
				this.estadoRepository = estadoRepository;
                this.prioridadRepository = prioridadRepository;
	}

    @Override
    public List<Incidencia> listar() {
        return incidenciaRepository.findAll();
    }

    @Override
    public Incidencia buscarPorId(Long id) {
        return incidenciaRepository.findById(id).orElse(null);
    }

    @Override
    public Incidencia guardar(Incidencia incidencia) {
        return incidenciaRepository.save(incidencia);
    }

    @Override
    public void eliminar(Integer id) {
        incidenciaRepository.deleteById(Long.valueOf(id));
    }

    @Override
    public void eliminar(Long id) {
        incidenciaRepository.deleteById(id);
    }

    @Override
    public void registrarIncidencia(IncidenciaRequest request) {
        Categoria categoria = categoriaRepository.findById(request.getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        
        Prioridad prioridad = prioridadRepository.findById(request.getIdPrioridad())
                .orElseThrow(() -> new RuntimeException("Prioridad no encontrada."));
        Usuario usuarioLogeado = usuarioRepository.findById(request.getIdUsuarioReportante()) 
                .orElseThrow(() -> new RuntimeException("Usuario reportante no encontrado con ID: " + request.getIdUsuarioReportante()));
        
        Area area = areaRepository.findById(request.getIdArea()) 
        	    .orElseThrow(() -> new RuntimeException("Área no encontrada."));
        
        StringBuilder detalle = new StringBuilder();
        for (Map.Entry<Long, String> entry : request.getRespuestas().entrySet()) {
            detalle.append("Pregunta ID ").append(entry.getKey())
                    .append(": ").append(entry.getValue()).append("\n");
        }
        
        Incidencia nueva = new Incidencia();
        
        nueva.setCategoria(categoria); 
        nueva.setPrioridad(prioridad); 
        nueva.setArea(area);           
        nueva.setUsuario(usuarioLogeado);
        
        nueva.setDscInc(detalle.toString()); 
        nueva.setObsInc(request.getObservacion()); 

        nueva.setFechaRegistro(LocalDateTime.now());
        
        Optional<Estado> estadoAbierta = estadoRepository.findByTipo("Abierta");
        if (estadoAbierta.isEmpty()) {
             throw new RuntimeException("El estado 'Abierta' no se encontró en la base de datos");
        }
        nueva.setEstado(estadoAbierta.get());
        
        String codigo = "INC-" + LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
        nueva.setCodigoReferencia(codigo);
        nueva.setTitulo("Incidencia generada automáticamente");
        
        incidenciaRepository.save(nueva);
    }
    
    @Override
    @Transactional
    public Incidencia asignarResponsable(Integer idIncidencia, Integer idResponsable, String observacion) {
        Usuario usuarioLogeado = usuarioRepository.findById(1)
                .orElseThrow(() -> new RuntimeException("Usuario por defecto (ID 1) no encontrado para seguimiento."));

        Incidencia incidencia = incidenciaRepository.findById(Long.valueOf(idIncidencia)) 
            .orElseThrow(() -> new RuntimeException("Incidencia con ID " + idIncidencia + " no encontrada."));

        Usuario nuevoResponsable = usuarioRepository.findById(idResponsable)
            .orElseThrow(() -> new RuntimeException("Usuario responsable con ID " + idResponsable + " no encontrado."));
        if (incidencia.getEstado().getId() == 3) {
             throw new IllegalStateException("La incidencia con ID " + idIncidencia + " está cerrada y no puede ser reasignada.");
        }
        incidencia.setResponsable(nuevoResponsable);
        Incidencia incidenciaActualizada = incidenciaRepository.save(incidencia);
        Seguimiento seguimiento = new Seguimiento();
        seguimiento.setIncidencia(incidenciaActualizada);
        seguimiento.setUsuario(usuarioLogeado);
        seguimiento.setFecha(LocalDateTime.now());
        
        String descripcionSeguimiento = String.format("Incidencia asignada a %s (%s). Estado actual: %s.", 
                                                    nuevoResponsable.getNombre(), 
                                                    nuevoResponsable.getUsername(),
                                                    incidenciaActualizada.getEstado().getTipo());
                                                    
        seguimiento.setDescripcion(descripcionSeguimiento);
        seguimiento.setObservacion(observacion); 
        seguimiento.setTipoAccion("ASIGNACION");
        
        seguimientoRepository.save(seguimiento);

        return incidenciaActualizada;
    }
    
    @Override
    public List<Incidencia> listarPorResponsable(Usuario responsable) {
        if (responsable == null) {
            System.err.println("Intento de listar incidencias con objeto responsable NULL en el Service.");
            return List.of();
        }
        return incidenciaRepository.findByResponsable(responsable);
    }

    @Override
    public List<Incidencia> listarPorUsuarioReportante(Usuario reportante) {
        if (reportante == null) {
            System.err.println("Intento de listar incidencias con objeto reportante NULL en el Service.");
            return List.of();
        }
        return incidenciaRepository.findByUsuario(reportante); 
    }
    @Override
    public Incidencia buscarUltimaIncidenciaPorUsuario(Integer idUsuario) {
        return incidenciaRepository.findTopByUsuario_IdOrderByFechaRegistroDesc(idUsuario);
    }
}