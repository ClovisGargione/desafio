/**
 * 
 */
package com.xpto.rest;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xpto.dto.Cidade;
import com.xpto.dto.Estados;
import com.xpto.entity.Cidades;
import com.xpto.service.CidadesService;

/**
 * @author clovis
 *
 */
@RestController
@RequestMapping(value = "/rest")
public class CidadesRest {
	
	@Autowired
	private CidadesService cidadesService;

	@GetMapping(path="/teste")
	public ResponseEntity<Object> teste() {
		 return ResponseEntity.ok("teste");
	}
	
	@GetMapping(path="/integracao")
	public ResponseEntity<Object> migracaoParaBaseDeDados(){
		try {
            List<Cidades> lista = cidadesService.integracaoCSVBaseDeDados();
            return ResponseEntity.ok(lista);
        } catch (Exception e) {
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
		
	}
	
	@GetMapping(path="/lista-de-capitais")
	public ResponseEntity<Object> listaDeCapitais(){
		List<Cidades> capitais;
		try {
			capitais = cidadesService.buscarListaDeCapitais();
			return ResponseEntity.ok(capitais);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
	}
	
	@GetMapping(path="/maior-menor-estado")
	public ResponseEntity<Object> estadoMaiorEMenor(){
		try {
			List<Estados> estados = cidadesService.estadoMaiorEMenor();
			return ResponseEntity.ok(estados);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@GetMapping(path="/quantidade-cidade-por-estado")
	public ResponseEntity<Object> quantidadeCidadesPorEstado(){
		try {
			List<Estados> estados = cidadesService.quantidadeDeCidadePorEstado();
			return ResponseEntity.ok(estados);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@GetMapping(path="/cidade/{ibgeId}")
	public ResponseEntity<Object> buscarCidadePorIbgeId(@PathVariable("ibgeId")	Long ibgeId){
		try {
			Cidades cidade = cidadesService.buscarCidadePorIbgeId(ibgeId);
			return ResponseEntity.ok(cidade);
		} catch (NoResultException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Serviço indiponível no momento.");
		}
	}
	
	@GetMapping(path="/estado/{uf}")
	public ResponseEntity<Object> buscarCidadesPorIbgeId(@PathVariable("uf") String uf){
		try {
			List<Cidade> cidades = cidadesService.buscarCidadesPorEstado(uf);
			return ResponseEntity.ok(cidades);
		} catch (NoResultException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Serviço indiponível no momento.");
		}
	}
	
	@PostMapping(path="/cidade/adicionar")
	public ResponseEntity<Object> adicionarCidade(@RequestBody Cidades cidade){
		try {
			cidadesService.adicionarCidade(cidade);
			return ResponseEntity.ok(cidade);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Serviço indiponível no momento.");
		}
	}
	
	@DeleteMapping(path="/cidade/remover/{ibgeId}")
	public ResponseEntity<Object> removerCidade(@PathVariable("ibgeId")	Long ibgeId){
		try {
			cidadesService.removerCidade(ibgeId);
			return ResponseEntity.ok().build();
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Serviço indiponível no momento.");
		}
	}

}
