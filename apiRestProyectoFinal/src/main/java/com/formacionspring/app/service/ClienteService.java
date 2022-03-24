package com.formacionspring.app.service;

import java.util.List;

import com.formacionspring.app.entity.Cliente;
import com.formacionspring.app.entity.VideoJuego;

public interface ClienteService {
	
	public List<Cliente> findAll();
	public Cliente findById(Long id);
	public List<VideoJuego> findAllVideojuegos();
	public Cliente save(Cliente cliente);
	public void delete(Long id);
	public Cliente deleteConRetorno(Long id);
}