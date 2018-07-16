/**
 * 
 */
package com.xpto.rest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.opencsv.CSVReader;
import com.xpto.entity.Cidades;
import com.xpto.repository.CidadesRepository;

/**
 * @author clovis
 *
 */
@RestController
@RequestMapping(value = "/rest")
public class CidadesRest {
	
	@Autowired
	private CidadesRepository cidadesRepository;
	

	@GetMapping(path="/teste")
	public ResponseEntity<Object> teste() {
		 CSVReader reader = null;
	        try {
	            reader = new CSVReader(new FileReader(ResourceUtils.getFile("classpath:csv/Cidades.csv")));
	            String[] line;
	            List<String> lista = new ArrayList<>();
	            while ((line = reader.readNext()) != null) {
	                System.out.println("Country [id= " + line[0] + ", code= " + line[1] + " , name=" + line[2] + "]");
	                lista.add("Country [id= " + line[0] + ", code= " + line[1] + " , name=" + line[2] + "]");
	            }
	            return ResponseEntity.ok(lista);
	        } catch (Exception e) {
	        	return ResponseEntity.notFound().build();
	        }
	}
	
	@SuppressWarnings("resource")
	@GetMapping(path="/integracao")
	public ResponseEntity<Object> migracaoParaBaseDeDados(){
		CSVReader reader = null;
		Cidades cidades = null;
		try {
            reader = new CSVReader(new FileReader(getFileFromResource()));
            String[] line;
            List<Cidades> lista = new ArrayList<>();
            reader.readNext();
            while ((line = reader.readNext()) != null) {
                cidades = new Cidades();
                cidades.setIbgeId(Long.parseLong(line[0]));
                cidades.setUf(line[1]);
                cidades.setNome(line[2]);
                cidades.setCapital(Boolean.parseBoolean(line[3]));
                cidades.setLongitude(Double.parseDouble(line[4]));
                cidades.setLatitude(Double.parseDouble(line[5]));
                cidades.setSemAcentos(line[6]);
                cidades.setNomesAlternativos(line[7]);
                cidades.setMicroregiao(line[8]);
                cidades.setMesoregiao(line[9]);
                cidadesRepository.save(cidades);
                lista.add(cidades);
            }
            return ResponseEntity.ok(lista);
        } catch (Exception e) {
        	return ResponseEntity.notFound().build();
        }
		
	}
	
	private File getFileFromResource() throws Exception {
		try {
			return ResourceUtils.getFile("classpath:csv/Cidades.csv");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new Exception("Não foi possível recuperar o arquivo");
		}
	}

}
