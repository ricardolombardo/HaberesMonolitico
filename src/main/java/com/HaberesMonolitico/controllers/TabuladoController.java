package com.HaberesMonolitico.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.HaberesMonolitico.services.TabuladoService;
import com.HaberesMonolitico.entities.Tabulado;

@RestController
@RequestMapping("/tabulados")
public class TabuladoController {

	@Autowired
	TabuladoService tabuladoService;
	
	@GetMapping("/getAll")
	public List<Tabulado> getAll(){
		 return tabuladoService.listarTabulados();
	}
	 
	@GetMapping("/{id}")
	public Optional<Tabulado> obtenerPorId(@PathVariable("id") Long id) {
		 return tabuladoService.obtenerTabuladoPorId(id);
	}
	  
	@GetMapping("/porLiquidacion")
	public List<Tabulado> getTabuladosDeUnaLiquidacion(@RequestParam("idLiquidacion") Long idLiquidacion) {
	     return tabuladoService.findByLiquidacionId(idLiquidacion);
	}
	
	//Controller para generar el PDF de un tabulado por liquidacion y persona
    @GetMapping("/recibo/{liq}/{per}")
    public ResponseEntity<byte[]> recibo(
    		@PathVariable("liq") Integer liq,
    		@PathVariable("per") Integer per) throws Exception {

        byte[] pdf = tabuladoService.generarRecibo(liq, per);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=recibo.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
    
  //Controller para generar el PDF de un tabulado por tabulado
    @GetMapping("/recibo/tabulado/{idTabulado}")
    public ResponseEntity<byte[]> reciboPorTabulado(
            @PathVariable ("idTabulado") Long idTabulado) throws Exception {

        byte[] pdf = tabuladoService.generarReciboPorTabulado(idTabulado);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=recibo.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }

    
	
}
