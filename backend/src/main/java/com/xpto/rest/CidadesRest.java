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
import com.xpto.dto.CidadesMaisDistantes;
import com.xpto.dto.DadosColuna;
import com.xpto.dto.Estados;
import com.xpto.dto.TotalDeRegistros;
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
	
	/**
	 * Ler o arquivo csv para a base de dados
	 * @return
	 */
	@GetMapping(path="/integracao")
	public ResponseEntity<Object> migracaoParaBaseDeDados(){
		try {
            List<Cidades> lista = cidadesService.integracaoCSVBaseDeDados();
            return ResponseEntity.ok(lista);
        } catch (Exception e) {
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
		
	}
	 /**
	  * Retornar somente as cidades que são capitais ordenadas pelo nome
	  * @return
	  */
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
	
	/**
	 * Maior e menor estado e quantidade de cidades
	 * @return
	 */
	@GetMapping(path="/maior-menor-estado")
	public ResponseEntity<Object> estadoMaiorEMenor(){
		try {
			List<Estados> estados = cidadesService.estadoMaiorEMenor();
			return ResponseEntity.ok(estados);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	/**
	 * Quantidade de cidades por estado
	 * @return
	 */
	@GetMapping(path="/quantidade-cidade-por-estado")
	public ResponseEntity<Object> quantidadeCidadesPorEstado(){
		try {
			List<Estados> estados = cidadesService.quantidadeDeCidadePorEstado();
			return ResponseEntity.ok(estados);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	/**
	 * Retorna cidade pelo id do ibge
	 * @param ibgeId
	 * @return
	 */
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
	
	/**
	 * Retorna cidades por estado
	 * @param uf
	 * @return
	 */
	@GetMapping(path="/estado/{uf}")
	public ResponseEntity<Object> buscarCidadesPorUf(@PathVariable("uf") String uf){
		try {
			List<Cidade> cidades = cidadesService.buscarCidadesPorEstado(uf);
			return ResponseEntity.ok(cidades);
		} catch (NoResultException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Serviço indiponível no momento.");
		}
	}
	
	/**
	 * Adiciona uma nova cidade
	 * @param cidade
	 * @return
	 */
	@PostMapping(path="/cidade/adicionar")
	public ResponseEntity<Object> adicionarCidade(@RequestBody Cidades cidade){
		try {
			cidadesService.adicionarCidade(cidade);
			return ResponseEntity.ok(cidade);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Serviço indiponível no momento.");
		}
	}
	
	/**
	 * Remove uma cidade pelo ibgeId
	 * @param ibgeId
	 * @return
	 */
	@DeleteMapping(path="/cidade/remover/{ibgeId}")
	public ResponseEntity<Object> removerCidade(@PathVariable("ibgeId")	Long ibgeId){
		try {
			cidadesService.removerCidade(ibgeId);
			return ResponseEntity.ok().build();
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Serviço indiponível no momento.");
		}
	}
	
	/**
	 * Retorna dados da coluna do csv que contenham a sequência de caracteres
	 * @param coluna
	 * @param caracteres
	 * @return
	 */
	@GetMapping(path="/dados/{coluna}/{caracteres}")
	public ResponseEntity<Object> dadosDaColunaPorString(@PathVariable("coluna") String coluna, @PathVariable("caracteres") String caracteres){
		List<String> lista;
		try {
			lista = cidadesService.dadosDaColunaPorString(coluna, caracteres);
			return ResponseEntity.ok(lista);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	/**
	 * Retorna a quantidade de dados distintos baseado em uma coluna
	 * @param coluna
	 * @return
	 */
	@GetMapping(path="/quantidade-de-dados/coluna/{coluna}")
	public ResponseEntity<Object> dadosDistintosPorColuna(@PathVariable("coluna") String coluna){
		 Long resultado;
		try {
			resultado = cidadesService.quantidadeDeItensDistintosPorColuna(coluna);
			return ResponseEntity.ok(new DadosColuna(coluna, resultado));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	/**
	 * Retorna a quantidade total de registros
	 * @return
	 */
	@GetMapping(path="/quantidade-total-de-registros")
	public ResponseEntity<Object> quantidadetotalDeRegistros(){
		 Long resultado;
		try {
			resultado = cidadesService.quantidadeTotalDeRegistros();
			return ResponseEntity.ok(new TotalDeRegistros(resultado));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	/**
	 * Retorna as cidades mais distantes e a distância em Km
	 * @return
	 */
	@GetMapping(path="/cidades-mais-distantes")
	public ResponseEntity<Object> cidadesMaisDistantes(){
		try {
			CidadesMaisDistantes cidadesMaisDistantes = cidadesService.calculaCidadesMaisDistantes();
			return ResponseEntity.ok(cidadesMaisDistantes);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

}
