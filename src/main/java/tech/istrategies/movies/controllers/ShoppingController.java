package tech.istrategies.movies.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.istrategies.movies.models.Shopping;
import tech.istrategies.movies.models.ShoppingDetail;
import tech.istrategies.movies.models.User;
import tech.istrategies.movies.services.MoviesService;
import tech.istrategies.movies.services.ShoppingDetailService;
import tech.istrategies.movies.services.ShoppingService;
import tech.istrategies.movies.services.UsersService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/shopping")
public class ShoppingController {
	
	@Autowired
	private ShoppingDetailService shoppingDetailService;
	
	@Autowired
	private ShoppingService shoppingService;
	
	@Autowired
	private UsersService userService;
	
	@Autowired
	private MoviesService movieService;
	

	@PostMapping("/{id_users}")
	public ResponseEntity<Object> Guardar(@RequestBody() List<ShoppingDetail> detail, @PathVariable("id_users") int id_users){
		
		if(detail.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.FOUND); 
			//return ResponseEntity.status(HttpStatus.FOUND).body(Map.of("message", "Seleccione las peliculas"));
		}else {
			Shopping shopp = new Shopping();
			User use = userService.findBy(id_users);
			shopp.setUsers(use);
			shopp.setState(true);
			Shopping shop = shoppingService.create(shopp);
			
			for(ShoppingDetail d: detail) {
				d.setShopping(shop);
				d.setPrice(movieService.returnSalePrice(d.getMovies().getIdMovies()));
			}
			shoppingDetailService.create(detail);
			
			return new ResponseEntity<>(HttpStatus.CREATED); 
			//return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Se ha agregado la pelicula"));
		}
	}
	
	@GetMapping
	public ResponseEntity<List<Object>> findAll(){
		List<Object> entities = new ArrayList<>();
		shoppingDetailService.getAllShopping().forEach(entities::add);
		
		if(entities.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(entities, HttpStatus.OK);
		}
	}
	
}
