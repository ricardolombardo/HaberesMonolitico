package com.HaberesMonolitico.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.HaberesMonolitico.entities.Concepto;
import com.HaberesMonolitico.repositories.ConceptoRepository;

@Service
public class ConceptoService {
	
	 @Autowired
	 private ConceptoRepository conceptoRepository;
	 
	 public List<Concepto> listarConceptos() {
		 return conceptoRepository.findAll();
	 }

}
