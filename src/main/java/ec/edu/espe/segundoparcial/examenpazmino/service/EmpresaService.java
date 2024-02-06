package ec.edu.espe.segundoparcial.examenpazmino.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.edu.espe.segundoparcial.examenpazmino.dao.EmpresaRepository;
import ec.edu.espe.segundoparcial.examenpazmino.domain.Empresa;
import ec.edu.espe.segundoparcial.examenpazmino.service.Exception.CreacionExcepcion;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public Empresa obtenerEmpresaPorRuc(String ruc){
        try {
            log.info("Se vaa  obtener la empresa con Ruc: {}", ruc);
            return empresaRepository.findByRuc(ruc);
        } catch (Exception e) {
            throw new CreacionExcepcion("Ocurrió un error obtener la empresa", e);
        }
    }

    @Transactional
    public void crear(Empresa empresa){
        try {
            empresaRepository.save(empresa);
            log.info("Se creo la empresa: {}", empresa);
        } catch (Exception e) {
            throw new CreacionExcepcion("Ocurrió un error al crear la empresa", e);
        }
        
    }

}
