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
	
	public List<Cidades> listaDeCapitais(){
		TypedQuery<Cidades> query = em.createQuery("select c from Cidades c where c.capital = true order by c.nome", Cidades.class);
		try {
			List<Cidades> cidades = query.getResultList();
			return cidades;
		}catch(NoResultException e) {
			return null;
		}
	}
	
	public List<Cidades> listaDecidadesOrdenadasPorEstado(){
		TypedQuery<Cidades> query = em.createQuery("select c from Cidades c order by c.uf", Cidades.class);
		try {
			List<Cidades> cidades = query.getResultList();
			return cidades;
		}catch(NoResultException e) {
			return null;
		}
	}

}
