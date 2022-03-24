package com.formacionspring.app.service;

import java.util.List;

import com.formacionspring.app.entity.VideoJuego;



public interface VideoJuegoService {

	public List<VideoJuego> findAll();
	public VideoJuego findById(Long id);
	public VideoJuego save(VideoJuego juego);
	public void delete(Long id);
	public VideoJuego deleteConRetorno(Long id);
}
