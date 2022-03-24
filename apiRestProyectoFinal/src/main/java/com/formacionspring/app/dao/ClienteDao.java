package com.formacionspring.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formacionspring.app.entity.Cliente;
import com.formacionspring.app.entity.VideoJuego;



@Repository
public interface ClienteDao extends CrudRepository<Cliente, Long> {
	//Para poder usar este método aquí (ya que es propio de otro repositorio):
	@Query("from VideoJuego")
	public List<VideoJuego> findAllVideojuegos();
}
