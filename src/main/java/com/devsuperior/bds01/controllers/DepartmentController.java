package com.devsuperior.bds01.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.bds01.dto.DepartmentDTO;
import com.devsuperior.bds01.services.DepartmentService;

@RestController // annotation que declara que este é um controlador REST. Um controlador controla os recursos das entity (objetos que podem representar a tabela do banco de dados)
@RequestMapping(value = "/departments") // rota
public class DepartmentController {
	
	@Autowired
	private DepartmentService service;
	
	@GetMapping // endpoint
	public ResponseEntity<List<DepartmentDTO>> findAll () {
		List<DepartmentDTO> list = service.findAll();
		
		return ResponseEntity.ok().body(list);
		
	}
}
