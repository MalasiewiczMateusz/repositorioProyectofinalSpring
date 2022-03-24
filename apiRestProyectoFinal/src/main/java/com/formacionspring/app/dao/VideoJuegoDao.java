package com.formacionspring.app.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formacionspring.app.entity.VideoJuego;

@Repository
public interface VideoJuegoDao extends CrudRepository<VideoJuego, Long>{

}
