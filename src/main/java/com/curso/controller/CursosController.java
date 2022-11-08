package com.curso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.curso.model.Curso;
import com.curso.service.CursosService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin("*")
@RestController
public class CursosController {
	
	@Autowired
	CursosService service;
	
	@ApiOperation(value = "Devuelve la lista con todos los cursos")
	@GetMapping(value="cursos", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> cursos() {
		return service.cursos();
	}
	
	@ApiOperation(value = "Añade un nuevo curso recibido en el cuerpo de la petición post y devuelve la lista con todos los cursos")
	@PostMapping(value="altaCurso", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> alta(@ApiParam(value = "JSON con los datos del curso") @RequestBody Curso curso) {
		return service.nuevoCurso(curso);
	}
	
	@ApiOperation(value = "Elimina un curso por su código de curso")
	@DeleteMapping(value="eliminarCurso/{codCurso}", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> eliminar(@ApiParam(value = "Código del curso a eliminar") @PathVariable("codCurso") String codCurso) {
		return service.eliminarCurso(codCurso);
	}
	
	@ApiOperation(value = "Actualiza la duración del curso")
	@PutMapping(value="actualizarDuracion/{codCurso}/{duracion}")
	public void actualizarDuracion(@ApiParam(value = "Código del curso al que se actualiza la duración") @PathVariable("codCurso") String codCurso, 
			@ApiParam(value = "Nueva duración del curso") @PathVariable("duracion") int duracion) {
		service.actualizarDuracionCurso(codCurso, duracion);
	}
	
	@ApiOperation(value = "Busca un curso por su código de curso")
	@GetMapping(value="curso/{codCurso}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Curso buscarCurso(@ApiParam(value = "Código del curso a buscar") @PathVariable("codCurso") String codCurso) {
		return service.buscarCurso(codCurso);
	}
	
	@ApiOperation(value = "Devuelve la lista con todos los cursos comprendidos entre dos precios")
	@GetMapping(value="cursosPorPrecio/{precioMin}/{precioMax}", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> cursosPorPrecio(@ApiParam(value = "Precio mínimo de los cursos que se buscan") @PathVariable("precioMin") Double precioMin, 
			@ApiParam(value = "Precio máximo de los cursos que se buscan") @PathVariable("precioMax") Double precioMax) {
		return service.cursoPorRangoDePrecio(precioMin, precioMax);
	}
	
	
}
