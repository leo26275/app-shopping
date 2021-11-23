package com.istrategies.movies.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.istrategies.movies.models.Movie;
import com.istrategies.movies.models.Reaction;
import com.istrategies.movies.repositories.ReactionRepository;

@Service
public class ReactionService {
	
	@Autowired
	private ReactionRepository reactionRepository;
	
	public Reaction create(Reaction reaction) {
		return reactionRepository.save(reaction);
	}
	
	public boolean isEmptyById(int id_reaction) {
		return reactionRepository.findById(id_reaction).isEmpty();
	}
	
	public Reaction findById(int id_reaction) {
		return reactionRepository.findById(id_reaction).get();
	}
	
	public List<Object> findAllReactions(){		
		return reactionRepository.findReactionsMovie();
	}
}
