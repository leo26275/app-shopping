package tech.istrategies.movies.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.istrategies.movies.models.Update;
import tech.istrategies.movies.services.UpdateService;

@RestController
@RequestMapping("/api/v1/update")
public class UpdateController {
	
	@Autowired
	private UpdateService updateService;
	
	@PostMapping
	public ResponseEntity<Object> create(@RequestBody Update update){
		//user.setState(true);
		Update use = updateService.create(update);
		if(use != null) {
			return new ResponseEntity<>(HttpStatus.CREATED);
			//return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("mensaje",  "Se creo el usuario con exito"));
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			//return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("mensaje", "Ha ocurrido un error"));
		}
	}
	
}
