package com.istrategies.movies.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.istrategies.movies.models.Reaction;
import com.istrategies.movies.services.MoviesService;
import com.istrategies.movies.services.ReactionService;
import com.istrategies.movies.services.UsersService;

@RestController
@RequestMapping("api/v1/reaction")
public class ReactionController {
	
	@Autowired
	private ReactionService reactionService;
	
	@Autowired
	private UsersService userService;
	
	@Autowired
	private MoviesService moviesService;
	
	@PostMapping("/{id_users}/{id_movies}")
	public ResponseEntity<Object> create(@PathVariable("id_users") int id_users, @PathVariable("id_movies") int id_movies){
		Reaction reaction = new Reaction();
		reaction.setUsers(userService.findBy(id_users));
		reaction.setMovies(moviesService.findBy(id_movies));
		reaction.setReaction(true);
		
		Reaction reac = reactionService.create(reaction);
		
		if(reac != null) {
			return new ResponseEntity<>(HttpStatus.CREATED);
			//return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("mensaje", "like"));
		}else {
			return new ResponseEntity<>(HttpStatus.FOUND);
			//return ResponseEntity.status(HttpStatus.FOUND).body(Map.of("mensaje", "Ocurrio un error"));
		}
	}
	
	@PutMapping("/{id_reaction}")
	public ResponseEntity<Object> update(@PathVariable("id_reaction") int id_reaction){
		boolean reac = reactionService.isEmptyById(id_reaction);
		
		if(reac) {
			return new ResponseEntity<>(HttpStatus.FOUND);
			//return ResponseEntity.status(HttpStatus.FOUND).body(Map.of("mensaje", "Ocurrio un error"));
		}
		
		Reaction newReaction = reactionService.findById(id_reaction);
		if(newReaction.isReaction()==true) {
			newReaction.setReaction(false);
			reactionService.create(newReaction);
			return new ResponseEntity<>(HttpStatus.OK);
			//return ResponseEntity.status(HttpStatus.OK).body(Map.of("mensaje", "Dislike"));
		}else {
			newReaction.setReaction(true);
			reactionService.create(newReaction);
			return new ResponseEntity<>(HttpStatus.OK);
			//return ResponseEntity.status(HttpStatus.OK).body(Map.of("mensaje", "Like"));
		}
	}
	
	@GetMapping("")
	public ResponseEntity<List<Object>> getAll(){
		List<Object> entities = new ArrayList<>();
		reactionService.findAllReactions().forEach(entities::add);
		
	//	JSONObject jsArray = new JSONObject(entities);
				
		if(entities.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(entities, HttpStatus.OK); 
		}
	}
	
}
