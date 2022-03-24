package com.formacionspring.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.formacionspring.app.entity.Cliente;
import com.formacionspring.app.entity.VideoJuego;
import com.formacionspring.app.service.ClienteService;

//Para autoriza a ángular (CORS)
@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ClienteRestController {
	@Autowired
	private ClienteService servicio;

	@GetMapping("/clientes")
	public List<Cliente> index() {
		return servicio.findAll();
	}

	// Si quiero que dos url utilicen el mismo método, por ejemplo:
	// @GetMapping({"/clientes","/todos"})
	/*
	 * @GetMapping("/clientes/{id}")//Paso el id en la dirección public Cliente
	 * findClienteById(@PathVariable Long id){ return servicio.findById(id); }
	 */

	@GetMapping("/clientes/{id}") // Paso el id en la dirección
	public ResponseEntity<?> findClienteById(@PathVariable Long id) {

		Cliente cliente = null;
		Map<String, Object> response = new HashMap<>();

		try {

			cliente = servicio.findById(id);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al reallizar consulta a base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		if (cliente == null) {
			response.put("mensaje", "El cliente ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}
	

	@PostMapping("/cliente/guardarCliente")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> saveCliente(@RequestBody Cliente cliente) {
		Map<String, Object> response = new HashMap<>();

		try {
			servicio.save(cliente);
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar la insert a la base de datos");
			response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "¡El cliente ha sido creado con exito!");
		response.put("cliente", cliente);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	
	
	@PutMapping("/cliente/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> upDateCliente(@RequestBody Cliente cliente, @PathVariable Long id) {

		Cliente clienteActual = servicio.findById(id);

		Map<String, Object> response = new HashMap<>();

		if (clienteActual == null) {
			response.put("mensaje", "El cliente ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			clienteActual.setId(id);
			clienteActual.setNombre(cliente.getNombre());
			clienteActual.setApellido(cliente.getApellido());
			clienteActual.setEmail(cliente.getEmail());
			clienteActual.setVideoJuego(cliente.getVideoJuego());
			

			servicio.save(clienteActual);
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar la actualización a la base de datos");
			response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "¡El cliente ha sido actualizado con exito!");
		response.put("cliente", clienteActual);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<?> deleteCliente(@PathVariable Long id) {

		Cliente clienteABorrar = servicio.findById(id);

		Map<String, Object> response = new HashMap<>();

		if (clienteABorrar == null) {
			response.put("mensaje", "El cliente ID: ".concat(id.toString().concat(" no se pudo eliminar")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			

			servicio.delete(id);

		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar la eliminación en la base de datos");
			response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "¡El cliente ha sido borrado con exito!");
		response.put("cliente", clienteABorrar);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	

	

	@GetMapping("/clientes/videojuegos")
	public List<VideoJuego> listarVideoJuegos() {
		return servicio.findAllVideojuegos();
	}

}
