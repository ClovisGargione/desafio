/**
 * 
 */
package com.xpto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xpto.entity.Cidades;

/**
 * @author clovis
 *
 */
@Repository
public interface CidadesRepository extends JpaRepository<Cidades, Long> {
	
	Cidades findByIbgeId(Long ibgeId);
	
	List<Cidades> findByUf(String uf);
}
