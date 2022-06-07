package com.devsuperior.bds02.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.services.CityService;

@RestController
@RequestMapping(value = "/cities")
public class CityController {

	@Autowired
	public CityService cityService;

	@GetMapping
	public ResponseEntity<List<CityDTO>> findAll() {
		List<CityDTO> list = cityService.findAll();
		return ResponseEntity.ok().body(list);
	}

	@PostMapping
	public ResponseEntity<CityDTO> insert(@RequestBody CityDTO dto) {

		dto = cityService.insert(dto);

		// Restornará o codigo 201 e também um parametro adicional no cabeçalho da
		// resposta
		// que será o endereço do novo recurso criado
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/cities/{id}").buildAndExpand(dto.getId())
				.toUri();
		return ResponseEntity.created(uri).body(dto);
	}
}
