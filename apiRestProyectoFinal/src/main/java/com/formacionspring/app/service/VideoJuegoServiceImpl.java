package com.formacionspring.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formacionspring.app.dao.VideoJuegoDao;
import com.formacionspring.app.entity.VideoJuego;


@Service
public class VideoJuegoServiceImpl implements VideoJuegoService{
	
	@Autowired
	private VideoJuegoDao videoJuegoDao;

	@Override
	public List<VideoJuego> findAll() {
		
		return (List<VideoJuego>) videoJuegoDao.findAll();
	}

	@Override
	public VideoJuego findById(Long id) {
		
		return videoJuegoDao.findById(id).orElse(null);
	}

	@Override
	public VideoJuego save(VideoJuego juego) {

		return videoJuegoDao.save(juego);
	}

	@Override
	public void delete(Long id) {
		videoJuegoDao.deleteById(id);
		
	}

	@Override
	public VideoJuego deleteConRetorno(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
