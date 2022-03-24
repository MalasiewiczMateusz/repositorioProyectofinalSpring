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

import com.formacionspring.app.entity.VideoJuego;
import com.formacionspring.app.service.VideoJuegoService;

//Para autoriza a ángular (CORS)
@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class VideoJuegoRestController {

	@Autowired
	private VideoJuegoService servicio;
	
	@GetMapping("/videojuegos")
	public List<VideoJuego> index(){
		return servicio.findAll();
	}
	
	
	@GetMapping("/videojuegos/{id}") // Paso el id en la dirección
	public ResponseEntity<?> findVideoJuegoById(@PathVariable Long id) {

		VideoJuego videoJuego = null;
		Map<String, Object> response = new HashMap<>();

		try {

			videoJuego = servicio.findById(id);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar consulta a base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		if (videoJuego == null) {
			response.put("mensaje", "El videojuego ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<VideoJuego>(videoJuego, HttpStatus.OK);
	}
	
	
	@PostMapping("/videojuego/guardarVideoJuego")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> saveVideoJuego(@RequestBody VideoJuego videoJuego) {
		Map<String, Object> response = new HashMap<>();

		try {
			servicio.save(videoJuego);
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar la insert a la base de datos");
			response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "¡El videojuego ha sido creado con exito!");
		response.put("videojuego", videoJuego);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
		
		@PutMapping("/videojuego/{id}")
		@ResponseStatus(HttpStatus.CREATED)
		public ResponseEntity<?> upDateVideoJuego(@RequestBody VideoJuego videoJuego, @PathVariable Long id) {

			VideoJuego VideoJuegoActual = servicio.findById(id);

			Map<String, Object> response = new HashMap<>();

			if (VideoJuegoActual == null) {
				response.put("mensaje", "El cliente ID: ".concat(id.toString().concat(" no existe en la base de datos")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}

			try {
				VideoJuegoActual.setId(id);
				VideoJuegoActual.setNombre(videoJuego.getNombre());
				

				servicio.save(VideoJuegoActual);
			} catch (DataAccessException e) {

				response.put("mensaje", "Error al realizar la actualización a la base de datos");
				response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));

				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			response.put("mensaje", "¡El videojuego ha sido actualizado con exito!");
			response.put("videojuego", VideoJuegoActual);

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		}
		
		@DeleteMapping("/videojuego/{id}")
		public ResponseEntity<?> deleteVideoJuego(@PathVariable Long id) {

			VideoJuego videoJuegoABorrar = servicio.findById(id);

			Map<String, Object> response = new HashMap<>();

			if (videoJuegoABorrar == null) {
				response.put("mensaje", "El videojuego ID: ".concat(id.toString().concat(" no se pudo eliminar")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}

			try {
				

				servicio.delete(id);

			} catch (DataAccessException e) {

				response.put("mensaje", "Error al realizar la eliminación en la base de datos");
				response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));

				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			response.put("mensaje", "¡El videojuego ha sido borrado con exito!");
			response.put("videojuego", videoJuegoABorrar);

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		}
	

}
