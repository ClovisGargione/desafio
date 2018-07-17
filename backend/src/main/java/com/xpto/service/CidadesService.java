package com.xpto.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import com.opencsv.CSVReader;
import com.xpto.dto.Cidade;
import com.xpto.dto.CidadesMaisDistantes;
import com.xpto.dto.Estados;
import com.xpto.entity.Cidades;
import com.xpto.repository.CidadesCustomRepository;
import com.xpto.repository.CidadesRepository;

@Configuration
public class CidadesService {
	
	@Autowired
	private CidadesRepository cidadesRepository;
	
	@Autowired
	private CidadesCustomRepository cidadesCustomRepository;
	
	@SuppressWarnings("resource")
	public List<Cidades> integracaoCSVBaseDeDados() throws Exception{
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
            return lista;
        } catch (Exception e) {
        	e.printStackTrace();
        	throw new Exception("Não foi possível migrar os dados do arquivo csv para a base de dados.");
        }
	}
	
	public List<Cidades> buscarListaDeCapitais() throws Exception{
		try {
		List<Cidades> cidades = cidadesCustomRepository.listaDeCapitais();
		return cidades;
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception("Não foi possível buscar a lista de capitais.");
		}
	}
	
	public List<Estados> estadoMaiorEMenor() throws Exception {
		List<Cidades> cidades = cidadesCustomRepository.listaDecidadesOrdenadasPorEstado();
		try {
			List<Estados> estados = estadoMaiorMenorNumeroCidades(cidades);
			return estados;			
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception("Não foi possível localizar a lista de estados.");
		}
	}
	
	public List<Estados> quantidadeDeCidadePorEstado() throws Exception{
		List<Cidades> cidades = cidadesCustomRepository.listaDecidadesOrdenadasPorEstado();
		try {
		List<Estados> estados = buscarQuantidadeDeCidadePorEstado(cidades);
		return estados;
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception("Não foi possível localizar a lista de estados.");
		}
	}
	
	public void adicionarCidade(Cidades cidade) {
		try {
			cidadesRepository.save(cidade);			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public Cidades buscarCidadePorIbgeId(Long ibgeId) throws Exception {
		Cidades cidade = cidadesRepository.findByIbgeId(ibgeId);
		if(cidade == null) {
			throw new NoResultException("Não foi encontrado nenhum resultado com ibgeId:" + ibgeId);
		}
		return cidade;
	}
	
	public List<Cidade> buscarCidadesPorEstado(String uf) throws Exception {
		List<Cidades> cidades = cidadesRepository.findByUf(uf.toUpperCase());
		List<Cidade> listaDeCidade = new ArrayList<>();
		for (Cidades cidade : cidades) {
			listaDeCidade.add(new Cidade(cidade.getNome()));
		}
		if(listaDeCidade.isEmpty()) {
			throw new NoResultException("Não foi encontrado nenhum resultado para o estado " + uf);
		}
		return listaDeCidade;
	}
	
	public void removerCidade(Long ibgeId) {
		try {
			cidadesRepository.deleteById(ibgeId);			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<String> dadosDaColunaPorString(String coluna, String caracteres) throws Exception{
		try {
				List<String> dados = cidadesCustomRepository.dadosDaColunaPorString(coluna, caracteres);							
				return dados;
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception("Não foi possível localizar os dados para coluna " + coluna);
		}
	}
	
	public Long quantidadeDeItensDistintosPorColuna(String coluna) throws Exception {
		try {
			Long quantidadeDeItens = cidadesCustomRepository.quantidadeDeItensDistintosPorColuna(coluna);
			return quantidadeDeItens;
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception("Não foi possível contar os dados para coluna " + coluna);
		}
	}
	
	public Long quantidadeTotalDeRegistros() throws Exception {
		try {
			Long resultado = cidadesCustomRepository.quantidadeTotalDeRegistros();
			return resultado;
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception("Não foi possível contar o total de registros");
		}
	}
	
	public CidadesMaisDistantes calculaCidadesMaisDistantes() {
		CidadesMaisDistantes cidadesMaisDistantes = new CidadesMaisDistantes();
		List<Cidades> listaCidades = cidadesRepository.findAll();
		Double distancia = 0.0;
		for(int index = 0; index < listaCidades.size(); index++) {
			for(int i = 0; i < listaCidades.size(); i++) {
				Double distanciaCalculada = CalculadoraDeDistancia.distancia(listaCidades.get(index).getLatitude(), listaCidades.get(index).getLongitude(), listaCidades.get(i).getLatitude(), listaCidades.get(i).getLongitude(), "K");
				if(distanciaCalculada > distancia) {
					distancia = distanciaCalculada;
					cidadesMaisDistantes = new CidadesMaisDistantes(listaCidades.get(index), listaCidades.get(i), String.format("%.3f", distanciaCalculada));
				}	
			}
		}
		return cidadesMaisDistantes;
	}
	
	private List<Estados> buscarQuantidadeDeCidadePorEstado(List<Cidades> lista){
		Map<String, List<Cidades>> map = mapEstados(lista);
		List<Estados> listaEstados = quantidadeDeCidadeporEstado(map);
		return listaEstados;
	}
	
	private List<Estados> estadoMaiorMenorNumeroCidades(List<Cidades> lista) {
		Estados estadosMaiorNumeroCidades = new Estados();
		Estados estadosMenorNumeroCidades = new Estados();
		Map<String, List<Cidades>> map = mapEstados(lista);
		List<Estados> listaEstados = new ArrayList<>();
		estadosMaiorNumeroCidades = maiorNumeroDeCidades(map);
		estadosMenorNumeroCidades = menorNumeroDeCidades(map);
		listaEstados.add(estadosMaiorNumeroCidades);
		listaEstados.add(estadosMenorNumeroCidades);
		return listaEstados;
	}
	
	private Map<String, List<Cidades>> mapEstados(List<Cidades> lista){
		Map<String, List<Cidades>> map = new HashMap<String, List<Cidades>>();
		List<Cidades> listaCidades = new ArrayList<Cidades>();
		String uf = lista.get(0).getUf();
		for (Cidades cidades : lista) {
			if(uf.equals(cidades.getUf())) {
				listaCidades.add(cidades);
			}else {
				map.put(uf, listaCidades);
				uf = cidades.getUf();
				listaCidades = new ArrayList<Cidades>();
				listaCidades.add(cidades);
			}
		}
		return map;
	}
	
	private List<Estados> quantidadeDeCidadeporEstado(Map<String, List<Cidades>> map){
		List<Estados> listaDeEstados = new ArrayList<>();
		for(Entry<String, List<Cidades>> entry : map.entrySet()) {
			String key = entry.getKey();
			List<Cidades> value = entry.getValue();
			listaDeEstados.add(new Estados(EstadosDoBrasil.estadoPorSigla(key), value.size()));
		}
		return listaDeEstados;
	}
	
	private Estados maiorNumeroDeCidades(Map<String, List<Cidades>> map) {
		Estados estadosMaiorNumeroCidades = new Estados("" , 0);
		for(Entry<String, List<Cidades>> entry : map.entrySet()) {
		    String key = entry.getKey();
		    List<Cidades> value = entry.getValue();
		    if(value.size() > estadosMaiorNumeroCidades.getQuantidadeDeCidades()) {
		    	estadosMaiorNumeroCidades = new Estados(EstadosDoBrasil.estadoPorSigla(key) , value.size());
		    }
		}
		return estadosMaiorNumeroCidades;
	}
	
	private Estados menorNumeroDeCidades(Map<String, List<Cidades>> map) {
		Estados estadosMenorNumeroCidades = new Estados("", 0);
		for(Entry<String, List<Cidades>> entry : map.entrySet()) {
			String key = entry.getKey();
			List<Cidades> value = entry.getValue();
			if(value.size() < estadosMenorNumeroCidades.getQuantidadeDeCidades() && value.size() > 1 || estadosMenorNumeroCidades.getQuantidadeDeCidades() == 0) {
				estadosMenorNumeroCidades = new Estados(EstadosDoBrasil.estadoPorSigla(key) , value.size());
		    }	
		}
		return estadosMenorNumeroCidades;
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
