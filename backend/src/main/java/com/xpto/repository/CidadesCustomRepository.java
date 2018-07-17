package com.xpto.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.xpto.entity.Cidades;

@Repository
@Transactional
public class CidadesCustomRepository {

	@PersistenceContext
	private EntityManager em;

	public List<Cidades> listaDeCapitais() {
		TypedQuery<Cidades> query = em.createQuery("select c from Cidades c where c.capital = true order by c.nome asc",
				Cidades.class);
		try {
			List<Cidades> cidades = query.getResultList();
			return cidades;
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<Cidades> listaDecidadesOrdenadasPorEstado() {
		TypedQuery<Cidades> query = em.createQuery("select c from Cidades c order by c.uf asc", Cidades.class);
		try {
			List<Cidades> cidades = query.getResultList();
			return cidades;
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<String> dadosDaColunaPorString(String coluna, String caracteres) {
		try {
			if (coluna.toLowerCase().equals("name")) {
				coluna = "nome";
			}
			if (coluna.toLowerCase().equals("no_accents")) {
				coluna = "semAcentos";
			}
			if (coluna.toLowerCase().equals("alternative_names")) {
				coluna = "nomesAlternativos";
			}
			if (coluna.toLowerCase().equals("microregion")) {
				coluna = "microregiao";
			}
			if (coluna.toLowerCase().equals("mesoregion")) {
				coluna = "mesoregiao";
			}
			String sql = "select c." + coluna + " from Cidades c where c." + coluna + " like '%" + caracteres + "%'";
			TypedQuery<String> query = em.createQuery(sql, String.class);
			List<String> dados = query.getResultList();
			return dados;
		} catch (NoResultException e) {
			return null;
		}
	}

	public Long quantidadeDeItensDistintosPorColuna(String coluna) {
		try {
			if (coluna.toLowerCase().equals("ibge_id")) {
				coluna = "ibgeId";
			}
			if (coluna.toLowerCase().equals("name")) {
				coluna = "nome";
			}
			if (coluna.toLowerCase().equals("no_accents")) {
				coluna = "semAcentos";
			}
			if (coluna.toLowerCase().equals("alternative_names")) {
				coluna = "nomesAlternativos";
			}
			if (coluna.toLowerCase().equals("microregion")) {
				coluna = "microregiao";
			}
			if (coluna.toLowerCase().equals("mesoregion")) {
				coluna = "mesoregiao";
			}
			if (coluna.toLowerCase().equals("lat")) {
				coluna = "latitude";
			}
			if (coluna.toLowerCase().equals("lon")) {
				coluna = "longitude";
			}
			TypedQuery<Long> query = em.createQuery("select count(distinct c." + coluna + ") from Cidades c",
					Long.class);
			Long resultado = (long) query.getSingleResult();
			return resultado;
		} catch (Exception e) {
			e.printStackTrace();
			return 0L;
		}
	}
	
	public Long quantidadeTotalDeRegistros() {
		try {
			TypedQuery<Long> query = em.createQuery("select count(c) from Cidades c",
					Long.class);
			Long resultado = (long) query.getSingleResult();
			return resultado;
		}catch(Exception e) {
			e.printStackTrace();
			return 0L;
		}
	}
}
