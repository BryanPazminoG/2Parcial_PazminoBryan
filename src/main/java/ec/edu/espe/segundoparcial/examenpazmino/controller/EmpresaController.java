package ec.edu.espe.segundoparcial.examenpazmino.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.espe.segundoparcial.examenpazmino.domain.Empresa;
import ec.edu.espe.segundoparcial.examenpazmino.service.EmpresaService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/empresas")
public class EmpresaController {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping("/{ruc}")
    public ResponseEntity<Empresa> buscarPorRuc(@PathVariable(name = "ruc") String ruc) {
        log.info("Obteniendo empresa con Ruc: {}", ruc);
        try {
            return ResponseEntity.ok(empresaService.obtenerEmpresaPorRuc(ruc));
        } catch(RuntimeException rte) {
            log.error("Error al obtener empresa por ruc.", rte);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> agregarEmpresa(@RequestBody Empresa empresa) {
        try {
            log.info("Va a crear una empresa: {}", empresa);
            empresaService.crear(empresa);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException rte){
            log.error("Error al crear un usuario de personal bancario.", rte);
            return ResponseEntity.badRequest().build();
        }
    }

}
