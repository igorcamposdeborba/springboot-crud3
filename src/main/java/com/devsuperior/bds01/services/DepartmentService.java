package com.devsuperior.bds01.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds01.dto.DepartmentDTO;
import com.devsuperior.bds01.entities.Department;
import com.devsuperior.bds01.repositories.DepartmentRepository;

@Service // annotation registra que essa classe faz parte do sistema automatizado do spring. Spring gerencia a injeção de dependência
public class DepartmentService {
	
	@Autowired
	private DepartmentRepository repository;
	
	public List<DepartmentDTO> findAll(){
		List<Department> list = repository.findAll(Sort.by("name"));
	
		// converter a lista de classe para DTO
		List<DepartmentDTO> listDepartmentDTO = new ArrayList<DepartmentDTO>();
		for(Department i : list) {
			listDepartmentDTO.add(new DepartmentDTO(i));
		}
		
		return listDepartmentDTO;
	}
	
	
	
	@Transactional(readOnly = true) // transação sempre executa esta operação no banco de dados. ReadOnly true não trava o banco (boa prática em operações de leitura)
	public Page<DepartmentDTO> findAllPaged(Pageable pageable) {
		Page<Department> list = repository.findAll(pageable); // buscar listas de departamentos
		
		// converter por meio do DTO a lista de departamentos
		List<DepartmentDTO> listDto = new ArrayList<DepartmentDTO>();
		for (Department dep : list) {
			listDto.add(new DepartmentDTO(dep));
		}
		Page<DepartmentDTO> page = new PageImpl<>(listDto); // converter a List em stream Page
		
		return page;
	}


}
