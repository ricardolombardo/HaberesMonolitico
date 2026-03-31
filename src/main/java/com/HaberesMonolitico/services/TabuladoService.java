package com.HaberesMonolitico.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HaberesMonolitico.entities.ExecutionConstant;
import com.HaberesMonolitico.entities.Liquidacion;
import com.HaberesMonolitico.entities.Tabulado;
import com.HaberesMonolitico.repositories.ExecutionConstantRepository;
import com.HaberesMonolitico.repositories.TabuladoRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@Service
public class TabuladoService {

	@Autowired
	private TabuladoRepository tabuladoRepository;
	
	@Autowired
	private ExecutionConstantRepository executionConstantRepository;
	
    @Autowired
    private DataSource dataSource;
	
	public List<Tabulado> listarTabulados(){
		return tabuladoRepository.findAll();
	}
	
	public Optional<Tabulado> obtenerTabuladoPorId(Long id) {
		return tabuladoRepository.findById(id);
	}
	
	public Tabulado guardarTabulado(Tabulado tabulado) {
		return tabuladoRepository.save(tabulado);
	}
	
	public void eliminarTabulado(Long id) {
		tabuladoRepository.deleteById(id);
	}
	
    public List<Tabulado> findByLiquidacionId(Long idLiquidacion) {
        return tabuladoRepository.findByLiquidacionId(idLiquidacion);
    }
    
    public byte[] generarRecibo(Integer idLiquidacion, Integer idPersona) throws Exception {
        InputStream jrxml = getClass().getResourceAsStream("/Reports/Tabulado.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jrxml);

        Map<String, Object> params = new HashMap<>();
        params.put("ID_LIQUIDACION", idLiquidacion);
        params.put("ID_PERSONA", idPersona);

        JasperPrint print = JasperFillManager.fillReport(
                jasperReport,
                params,
                dataSource.getConnection()
        );

        return JasperExportManager.exportReportToPdf(print);
    }

    public byte[] generarReciboPorTabulado(Long idTabulado) throws Exception {

        Tabulado tabulado = tabuladoRepository.findById(idTabulado)
                .orElseThrow(() -> new RuntimeException("Tabulado no encontrado"));

        ExecutionConstant reciboPathConst = executionConstantRepository
                .findByKey("RECIBO_PATH")
                .orElseThrow(() -> new RuntimeException("No existe la constante RECIBO_PATH"));

        JasperReport jasperReport = cargarReporte(reciboPathConst.getValue());

        Liquidacion liquidacion = tabulado.getLiquidacion();

        Map<String, Object> params = new HashMap<>();
        params.put("ID_LIQUIDACION", liquidacion.getId().intValue());
        params.put("ID_PERSONA", tabulado.getNou().getPersona().getId().intValue());

        JasperPrint print = JasperFillManager.fillReport(
                jasperReport,
                params,
                dataSource.getConnection()
        );

        return JasperExportManager.exportReportToPdf(print);
    }

    
    private JasperReport cargarReporte(String basePath) throws JRException {
        File jasperFile = new File(basePath + ".jasper");
        File jrxmlFile  = new File(basePath + ".jrxml");

        // PRODUCCIÓN (o cuando ya está compilado)
        if (jasperFile.exists()) {
            return (JasperReport) JRLoader.loadObject(jasperFile);
        }

        // DESARROLLO
        if (jrxmlFile.exists()) {
            return JasperCompileManager.compileReport(jrxmlFile.getAbsolutePath());
        }

        throw new RuntimeException(
            "No se encontró el reporte ni en formato .jasper ni .jrxml. Path base: " + basePath
        );
    }


    
    
	
}
